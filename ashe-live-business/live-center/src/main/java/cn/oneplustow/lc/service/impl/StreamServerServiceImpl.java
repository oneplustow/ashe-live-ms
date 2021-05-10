package cn.oneplustow.lc.service.impl;

import cn.oneplustow.common.mapstruct.MapStructContext;
import cn.oneplustow.common.verify.ValidatorContext;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.lc.entity.StreamServer;
import cn.oneplustow.lc.mapper.StreamServerMapper;
import cn.oneplustow.lc.service.IOssrsService;
import cn.oneplustow.lc.service.IStreamServerService;
import cn.oneplustow.lc.vo.QueryStreamServerDto;
import cn.oneplustow.lc.vo.SaveStreamServerDto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.oneplustow.common.constant.DbConstants.SteamServerStatus.CONNECTION_FAILURE;
import static cn.oneplustow.common.constant.DbConstants.SteamServerStatus.CONNECTION_SUCCESS;

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
    @Autowired
    private MapStructContext mapStructContext;

    @Autowired
    private ValidatorContext validatorContext;

    @Autowired
    private IOssrsService iOssrsService;

    @Override
    public boolean delById(List<Integer> idList) {
        return this.removeByIds(idList);
    }

    @Override
    public boolean addStreamServer(SaveStreamServerDto streamServerDto) {
        validatorContext.validate(streamServerDto);
        StreamServer streamServer = mapStructContext.conver(streamServerDto, StreamServer.class);
        return this.save(streamServer);
    }

    @Override
    public boolean updateStreamServer(SaveStreamServerDto streamServerDto) {
        validatorContext.validate(streamServerDto);
        StreamServer streamServer = mapStructContext.conver(streamServerDto, StreamServer.class);
        return this.updateById(streamServer);
    }


    @Override
    public StreamServer getStreamServerById(Long id) {
        return this.getById(id);
    }

    @Override
    public List<StreamServer> page(QueryStreamServerDto streamServer){
        PageUtil.startPage();
        QueryWrapper<StreamServer> queryWrapper = new QueryWrapper<StreamServer>();
        if (StringUtils.isNotBlank(streamServer.getServerName())){
            queryWrapper.like("server_name" ,streamServer.getServerName());
        }
        if(StringUtils.isNotBlank(streamServer.getIp())){
            queryWrapper.like("ip" ,streamServer.getIp());
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
    public StreamServer getAvailable() {
        StreamServer streamServer = this.getOne(new LambdaQueryWrapper<StreamServer>()
                .eq(StreamServer::getStatus, CONNECTION_SUCCESS)
                .orderByDesc(StreamServer::getCpuUse), false);
        return streamServer;

    }

    @Override
    public void heartBeatDetection() {
        List<StreamServer> streamServerList = this.list();
        for (StreamServer streamServer : streamServerList) {
            if(heartBeatDetection(streamServer.getIp(),streamServer.getPort())){
                streamServer.setStatus(CONNECTION_SUCCESS);
                continue;
            }
            streamServer.setStatus(CONNECTION_FAILURE);
        }
        updateBatchById(streamServerList);
    }

    @Override
    public boolean heartBeatDetectionById(Long id) {
        StreamServer streamServer = this.getById(id);
        boolean success = false;
        //这里如果解析错误，则说明返回的不是json
        if (heartBeatDetection(streamServer.getIp(),streamServer.getPort())) {
            success = true;
        }
        streamServer.setStatus(success ? CONNECTION_SUCCESS : CONNECTION_FAILURE);
        updateById(streamServer);
        return success;
    }

    @Override
    public boolean heartBeatDetection(String ip,Integer port) {
        return iOssrsService.connectinoOssrsServer(ip, port);
    }
}
