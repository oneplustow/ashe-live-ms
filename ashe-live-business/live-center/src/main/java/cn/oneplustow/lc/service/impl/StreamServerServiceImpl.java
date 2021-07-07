package cn.oneplustow.lc.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.opl.generate.QueryUtil;
import cn.opl.mapstruct.MapStructContext;
import cn.oneplustow.common.verify.ValidatorContext;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.lc.entity.StreamServer;
import cn.oneplustow.lc.mapper.StreamServerMapper;
import cn.oneplustow.lc.service.IOssrsService;
import cn.oneplustow.lc.service.IStreamServerService;
import cn.oneplustow.lc.vo.QueryStreamServerQueryCriteria;
import cn.oneplustow.lc.vo.SaveStreamServerDto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.oneplustow.common.constant.DbConstants.SteamServerStatus.*;

/**
 * 流服务器信息Service业务层处理
 *
 * @author cc
 * @date 2021-04-09
 */
@Slf4j
@Service
public class StreamServerServiceImpl extends ServiceImpl<StreamServerMapper, StreamServer> implements IStreamServerService {

    @Autowired
    private MapStructContext mapStructContext;

    @Autowired
    private ValidatorContext validatorContext;

    @Autowired
    private IOssrsService ossrsService;

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
    public List<StreamServer> page(QueryStreamServerQueryCriteria streamServer){
        PageUtil.startPage();
        QueryWrapper<StreamServer> queryWrapper = QueryUtil.generate(streamServer);
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
        List<StreamServer> streamServerList = this.list(new LambdaQueryWrapper<StreamServer>()
                .ne(StreamServer::getStatus,DISABLE));
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
        if (StrUtil.equals(streamServer.getStatus(),DISABLE)) {
            throw new WarningMessageException("当前服务器为禁用状态，不允许进行连接操作");
        }
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
    public boolean disableOrEnableStreamServer(Integer id,boolean enable) {
        String status = DISABLE;
        if(enable){
            StreamServer server = this.getById(id);
            if(server == null){throw new WarningMessageException("服务器信息不存在");}
            boolean connectionSuccess = heartBeatDetection(server.getIp(), server.getPort());
            status = connectionSuccess ? CONNECTION_SUCCESS : CONNECTION_FAILURE;
        }
        this.update(new LambdaUpdateWrapper<StreamServer>()
                .set(StreamServer::getStatus,status)
                .eq(StreamServer::getId,id));
        return true;
    }

    @Override
    public boolean heartBeatDetection(String ip,Integer port) {
        return ossrsService.connectinoOssrsServer(ip, port);
    }
}
