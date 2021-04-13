package cn.oneplustow.live.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.oneplustow.common.constant.Constants;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.live.entity.StreamServer;
import cn.oneplustow.live.mapper.StreamServerMapper;
import cn.oneplustow.live.service.IStreamServerService;
import com.alibaba.druid.sql.visitor.functions.If;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流服务器信息Service业务层处理
 *
 * @author cc
 * @date 2021-04-09
 */
@Slf4j
@Service
public class StreamServerServiceImpl extends ServiceImpl<StreamServerMapper, StreamServer> implements IStreamServerService {

    private static final String URL_TEMPLATE = "http://{}:{}/api/v1/";


    @Override
    public List<StreamServer> page(StreamServer streamServer){
        PageUtil.startPage();
        QueryWrapper<StreamServer> queryWrapper = new QueryWrapper<StreamServer>();
        if (StringUtils.isNotBlank(streamServer.getServerName())){
            queryWrapper.like("server_name" ,streamServer.getServerName());
        }
        if(StringUtils.isNotBlank(streamServer.getIp())){
            queryWrapper.eq("ip" ,streamServer.getIp());
        }
        if(streamServer.getPort() != null){
            queryWrapper.eq("port" ,streamServer.getPort());
        }
        if(StringUtils.isNotBlank(streamServer.getStatus())){
            queryWrapper.eq("status" ,streamServer.getStatus());
        }
        List<StreamServer> list = this.list(queryWrapper);
        return list;
    }

    @Override
    public void heartBeatDetection() {
        List<StreamServer> streamServerList = this.list();
        for (StreamServer streamServer : streamServerList) {
            try {
                if(heartBeatDetection(streamServer.getIp(),streamServer.getPort())){
                    streamServer.setStatus(Constants.SteamServerStatus.CONNECTION_SUCCESS);
                    continue;
                }
            }catch (Exception e){
                log.warn("流服务心跳解析失败...",e);
            }
            streamServer.setStatus(Constants.SteamServerStatus.CONNECTION_FAILURE);
        }
        updateBatchById(streamServerList);
    }

    @Override
    public boolean heartBeatDetectionById(Integer id) {
        StreamServer streamServer = this.getById(id);
        boolean success = false;
        //这里如果解析错误，则说明返回的不是json
        try {
            if (heartBeatDetection(streamServer.getIp(),streamServer.getPort())) {
                success = true;
            }
        }catch (Exception e){
            log.warn("流服务心跳解析失败...",e);
        }
        streamServer.setStatus(success?Constants.SteamServerStatus.CONNECTION_SUCCESS:
            Constants.SteamServerStatus.CONNECTION_FAILURE);
        updateById(streamServer);
        return success;
    }

    @Override
    public boolean heartBeatDetectionTest(String ip,Integer port) {
        //这里如果解析错误，则说明返回的不是json
        try {
            return heartBeatDetection(ip,port);
        }catch (Exception e){
            log.warn("流服务心跳解析失败...",e);
        }
        return false;
    }


    @Override
    public boolean heartBeatDetection(String ip,Integer port)throws Exception {
        String url = StrUtil.format(URL_TEMPLATE,ip,port);
        //这里如果解析错误，则说明返回的不是json
        String serverJsonRequest = HttpUtil.get(url);
        JSONObject serverRequest = JSONObject.parseObject(serverJsonRequest);
        int code = serverRequest.getIntValue("code");
        String serverId = serverRequest.getString("server");
        //如果状态和serviceid 都OK 则说明服务器在线
        return (code == 0 && StrUtil.isNotBlank(serverId));
    }
}
