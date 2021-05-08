package cn.oneplustow.lc.service.impl;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.mapstruct.MapStructContext;
import cn.oneplustow.lc.entity.PlayRoom;
import cn.oneplustow.lc.entity.StreamServer;
import cn.oneplustow.lc.entity.StreamServerAllotRecord;
import cn.oneplustow.lc.mapper.StreamServerAllotRecordMapper;
import cn.oneplustow.lc.service.IStreamServerAllotRecordService;
import cn.oneplustow.lc.service.IStreamServerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static cn.oneplustow.common.constant.DbConstants.SteamServerAllotRecordStatus.INVALID;
import static cn.oneplustow.common.constant.DbConstants.SteamServerAllotRecordStatus.NORMAL;

/**
 * 直播间管理Service业务层处理
 * 
 * @author cc
 * @date 2021-04-14
 */
@Slf4j
@Service
public class StreamServerAllotRecordServiceImpl extends ServiceImpl<StreamServerAllotRecordMapper, StreamServerAllotRecord> implements IStreamServerAllotRecordService
{
    private final String streamTemplate = "rtmp:{}:{}/{}";
    @Autowired
    private IStreamServerService streamServerService;

    @Autowired
    private MapStructContext mapStructContext;

    @Override
    public Boolean invalidRecord(Long playRoomId){
        this.update(this.lambdaUpdate().eq(StreamServerAllotRecord::getPlayRoomId,playRoomId)
        .eq(StreamServerAllotRecord::getStatus,NORMAL).set(StreamServerAllotRecord::getStatus,INVALID));
        return true;
    }

    @Override
    public StreamServerAllotRecord getAllotRecordByPlayRoomId(Long playRoomId){
        StreamServerAllotRecord allotRecord = this.getOne(this.lambdaQuery()
                .eq(StreamServerAllotRecord::getPlayRoomId, playRoomId)
                .eq(StreamServerAllotRecord::getStatus, NORMAL));
        return allotRecord;
    }

    @Override
    public StreamServerAllotRecord allotStreamServer(PlayRoom playRomm){
        StreamServerAllotRecord allotRecord = getAllotRecordByPlayRoomId(playRomm.getId());
        if(allotRecord != null){return allotRecord;}
        StreamServer available = streamServerService.getAvailable();
        String streamUrl = getStreamUrl(available, playRomm);
        StreamServerAllotRecord serverAllotRecord = StreamServerAllotRecord.builder()
                .playRoomId(playRomm.getId())
                .streamServerId(available.getId())
                .pushStreamUrl(streamUrl)
                .pushStreamPassword(RandomUtil.randomNumbers(6))
                .createTime(new Date())
                .build();
        this.save(serverAllotRecord);
        return serverAllotRecord;
    }

    /**
     * 选取一个可用的服务器用于进行构建推流连接
     * @param playRoom
     * @return
     */
    private String getStreamUrl(StreamServer streamServer,PlayRoom playRoom) {
        String ip = streamServer.getIp();
        Integer port = streamServer.getPort();
        return StrUtil.format(streamTemplate,ip,port,playRoom.getRoomNumbe());
    }

}