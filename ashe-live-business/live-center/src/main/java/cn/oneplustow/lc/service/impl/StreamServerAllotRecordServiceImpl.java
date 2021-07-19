package cn.oneplustow.lc.service.impl;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.lc.entity.PlayRoom;
import cn.oneplustow.lc.entity.StreamServer;
import cn.oneplustow.lc.entity.StreamServerAllotRecord;
import cn.oneplustow.lc.mapper.StreamServerAllotRecordMapper;
import cn.oneplustow.lc.service.IStreamServerAllotRecordService;
import cn.oneplustow.lc.service.IStreamServerService;
import cn.opl.mapstruct.MapStructContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

import static cn.oneplustow.common.constant.DbConstants.SteamServerAllotRecordStatus.INVALID;
import static cn.oneplustow.common.constant.DbConstants.SteamServerAllotRecordStatus.NORMAL;

/**
 * 流服务器分配记录服务层
 * 
 * @author cc
 * @date 2021-04-14
 */
@Slf4j
@Service
public class StreamServerAllotRecordServiceImpl extends ServiceImpl<StreamServerAllotRecordMapper, StreamServerAllotRecord> implements IStreamServerAllotRecordService
{
    private final String PULL_STREAM_TEMPLATE = "rtmp://{}/{}";
    private final String PLAY_STREAM_TEMPLATE = "{}://{}:8080/{}/{}.flv";

    @Value("${protocol:'http'}")
    private String protocol;

    @Autowired
    private IStreamServerService streamServerService;

    @Autowired
    private MapStructContext mapStructContext;

    @Override
    public Boolean invalidRecord(Long playRoomId){
        this.update(new LambdaUpdateWrapper<StreamServerAllotRecord>()
                .eq(StreamServerAllotRecord::getPlayRoomId,playRoomId)
                .eq(StreamServerAllotRecord::getStatus,NORMAL)
                .set(StreamServerAllotRecord::getStatus,INVALID));
        return true;
    }

    @Override
    public StreamServerAllotRecord getAllotRecordByPlayRoomId(Long playRoomId){
        StreamServerAllotRecord allotRecord = this.getOne(
                new LambdaQueryWrapper<StreamServerAllotRecord>()
                .eq(StreamServerAllotRecord::getPlayRoomId, playRoomId)
                .eq(StreamServerAllotRecord::getStatus, NORMAL));
        return allotRecord;
    }

    @Override
    public StreamServer getAllotStreamServer(Long playRoomId){
        StreamServerAllotRecord streamServerAllotRecord = getAllotRecordByPlayRoomId(playRoomId);
        Long streamServerId = streamServerAllotRecord.getStreamServerId();
        return streamServerService.getStreamServerById(streamServerId);
    }

    @Override
    public StreamServerAllotRecord allotStreamServer(PlayRoom playRoom){
        StreamServerAllotRecord allotRecord = getAllotRecordByPlayRoomId(playRoom.getId());
        if(allotRecord != null){return allotRecord;}
        StreamServer available = streamServerService.getAvailable();

        String password = RandomUtil.randomNumbers(6);
        String pullStreamUrl = getPullStreamUrl(available, playRoom);
        String playStreamUrl = getPlayStreamUrl(available, playRoom,password);
        StreamServerAllotRecord serverAllotRecord = StreamServerAllotRecord.builder()
                .playRoomId(playRoom.getId())
                .streamServerId(available.getId())
                .pushStreamUrl(pullStreamUrl)
                .playStreamUrl(playStreamUrl)
                .pushStreamPassword(password)
                .createTime(new Date())
                .status(NORMAL)
                .build();
        this.save(serverAllotRecord);
        return serverAllotRecord;
    }

    private String getPlayStreamUrl(StreamServer streamServer, PlayRoom playRoom,String password) {
        String ip = streamServer.getIp();
        return StrUtil.format(PLAY_STREAM_TEMPLATE,protocol,ip,playRoom.getRoomNumbe(),password);
    }

    /**
     * 选取一个可用的服务器用于进行构建推流连接
     * @param playRoom
     * @return
     */
    private String getPullStreamUrl(StreamServer streamServer, PlayRoom playRoom) {
        String ip = streamServer.getIp();
        return StrUtil.format(PULL_STREAM_TEMPLATE,ip,playRoom.getRoomNumbe());
    }

}
