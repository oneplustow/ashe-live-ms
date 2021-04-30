package cn.oneplustow.lc.service.impl;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.common.mapstruct.MapStructContext;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.lc.entity.PlayRoom;
import cn.oneplustow.lc.entity.StreamServer;
import cn.oneplustow.lc.mapper.PlayRoomMapper;
import cn.oneplustow.lc.service.IOssrsService;
import cn.oneplustow.lc.service.IPlayRoomService;
import cn.oneplustow.lc.service.IStreamServerService;
import cn.oneplustow.lc.vo.PlayRoomDetailVo;
import cn.oneplustow.lc.vo.QueryPlayRoomDto;
import cn.oneplustow.lc.vo.SavePlayRoomDto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.oneplustow.common.constant.DbConstants.PalyRoomStatus.*;

/**
 * 直播间管理Service业务层处理
 * 
 * @author cc
 * @date 2021-04-14
 */
@Slf4j
@Service
public class PlayRoomServiceImpl extends ServiceImpl<PlayRoomMapper, PlayRoom> implements IPlayRoomService
{
    private final String streamTemplate = "rtmp:{}:{}/{}";
    @Autowired
    private IStreamServerService streamServerService;

    @Autowired
    private MapStructContext mapStructContext;

    @Autowired
    private IOssrsService ossrsService;

    @Override
    public Boolean delPlayRoom(List<Long> idList) {
        return this.removeByIds(idList);
    }

    @Override
    public Boolean savePlayRoom(SavePlayRoomDto savePlayRoomDto) {
        PlayRoom playRoom = mapStructContext.conver(savePlayRoomDto, PlayRoom.class);
        if(playRoom.getId() == null){
            initPlayRoom(playRoom);
            return this.save(playRoom);
        }
        return this.updateById(playRoom);
    }

    private void initPlayRoom(PlayRoom playRoom){
        Long userId = playRoom.getUserId();
        int count = this.count(new LambdaQueryWrapper<PlayRoom>().eq(PlayRoom::getUserId, userId));
        Assert.isTrue(count == 0,"当前用户已经拥有直播间");
        if(StrUtil.isBlank(playRoom.getRoomNumbe())) {
            playRoom.setRoomNumbe(IdUtil.simpleUUID());
        }
        playRoom.setStreamPassword(RandomUtil.randomString(6));
    }

    @Override
    public PlayRoomDetailVo getPlayRoomDetailVoByIdOrUserId(Long id, Long userId) {
        PlayRoom playRoom = getPlayRoomByIdOrUserId(id,userId);
        PlayRoomDetailVo playRoomDetailVo = mapStructContext.conver(playRoom, PlayRoomDetailVo.class);
        // todo 这里应该从用户中心获取
        playRoomDetailVo.setUserName("张三");
        playRoomDetailVo.setSteamUrl(getStreamUrl(playRoom));
        return playRoomDetailVo;
    }


    private PlayRoom getPlayRoomByIdOrUserId(Long id,Long userId) {
        LambdaQueryChainWrapper<PlayRoom> wrapper = this.lambdaQuery();
        if(id != null){wrapper.eq(PlayRoom::getId,id);}
        if(userId != null){wrapper.eq(PlayRoom::getUserId,userId);}
        return this.getOne(wrapper);
    }

    /**
     * 选取一个可用的服务器用于进行构建推流连接
     * @param playRoom
     * @return
     */
    private String getStreamUrl(PlayRoom playRoom) {
        StreamServer available = streamServerService.getAvailable();
        String ip = available.getIp();
        Integer port = available.getPort();
        return StrUtil.format(streamTemplate,ip,port,playRoom.getRoomNumbe());
    }

    @Override
    public List<PlayRoom> selectPage(QueryPlayRoomDto playRoom) {
        PageUtil.startPage();
        LambdaQueryWrapper<PlayRoom> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(playRoom.getName())) {
            queryWrapper.like(PlayRoom::getName, playRoom.getName());
        }
        if (playRoom.getUserId() != null) {
            queryWrapper.eq(PlayRoom::getUserId, playRoom.getUserId());
        }
        if (StrUtil.isNotBlank(playRoom.getStatus())) {
            queryWrapper.eq(PlayRoom::getStatus, playRoom.getStatus());
        }
        List<PlayRoom> list = this.list(queryWrapper);
        return list;
    }

    @Override
    public boolean openUp(String name, Long userId) {
        SavePlayRoomDto savePlayRoomDto = new SavePlayRoomDto();
        savePlayRoomDto.setName(name);
        savePlayRoomDto.setUserId(userId);
        return this.savePlayRoom(savePlayRoomDto);
    }

    @Override
    public PlayRoomDetailVo startLive(Long userId) {
        PlayRoom playRoom = getPlayRoomByIdOrUserId(null, userId);
        Assert.notNull(playRoom,"您已经在直播中");
        Assert.isFalse(playRoom.getStatus() == WAIT_PUSH,"您已经在直播中");
        Assert.isFalse(playRoom.getStatus() == START,"您已经在直播中");

        playRoom.setStreamPassword(RandomUtil.randomString(6));
        playRoom.setStatus(WAIT_PUSH);
        this.updateById(playRoom);
        PlayRoomDetailVo playRoomDetailVo = mapStructContext.conver(playRoom, PlayRoomDetailVo.class);
        playRoomDetailVo.setSteamUrl(getStreamUrl(playRoom));
        return playRoomDetailVo;
    }

    @Override
    public Boolean viewPlay(String roomNumber){
        return updateViewNumber(roomNumber,1);
    }
    @Override
    public Boolean unViewPlay(String id) {
        return updateViewNumber(id,-1);
    }

    private Boolean updateViewNumber(String roomNumber, Integer count){
        while (true) {
            //1 查询出现在的观看数量
            PlayRoom playRoom = this.getOne(new LambdaQueryWrapper<PlayRoom>().eq(PlayRoom::getRoomNumbe,roomNumber));
            if (playRoom == null) {
                throw new WarningMessageException("直播间不存在");
            }
            //2 增加观看数量
            Long clickCount = playRoom.getViewNumber();
            Long newClickCount = clickCount + count;
            //3 用cas更新到数据库
            boolean success = this.update(new LambdaUpdateWrapper<PlayRoom>()
                    .set(PlayRoom::getViewNumber, newClickCount)
                    .eq(PlayRoom::getRoomNumbe, roomNumber)
                    .eq(PlayRoom::getViewNumber, clickCount));
            if(success){return true;}
        }
    }


    @Override
    public Boolean startPush(String roomNumbe, String password,String clientId) {
        PlayRoom playRoom = getPlayRoomByAppAndPassword(roomNumbe, password);
        if(playRoom == null){
            log.info("房间号或密码错误：app:{},password:{}",roomNumbe,password);
            return false;
        }
        if(StrUtil.equals(playRoom.getStatus(),NOT_START)){
            log.info("用户未开始直播，就直接推流");
            return false;
        }
        //先干掉之前的推流 然后接入新的推流
        if(StrUtil.equals(playRoom.getStatus(),START)){
            log.info("用户已经推流了，但是再次推流");
            // 加入踢流的逻辑
            String odlClientId = playRoom.getClientId();
            //踢流
            //ossrsService.eliminateStream(streamServer.getIp(),streamServer.getPort(),clientId);
        }
        playRoom.setStatus(START);
        playRoom.setClientId(clientId);
        playRoom.setStreamPassword(RandomUtil.randomString(6));
        return this.updateById(playRoom);
    }

    @Override
    public Boolean stopPush(String roomNumbe, String password) {
        return updateStatusByAppAndApssword(roomNumbe,password,null,WAIT_PUSH);
    }

    private PlayRoom getPlayRoomByAppAndPassword(String roomNumbe,String password){
        PlayRoom playRoom = this.getOne(new LambdaQueryWrapper<PlayRoom>()
                .eq(PlayRoom::getRoomNumbe, roomNumbe)
                .eq(PlayRoom::getStreamPassword, password));
        return playRoom;
    }

    private Boolean updateStatusByAppAndApssword(String roomNumbe,String password,String oldStatus,String newStatus){
        LambdaUpdateWrapper<PlayRoom> lambdaUpdateWrapper = new LambdaUpdateWrapper<PlayRoom>()
                .eq(PlayRoom::getRoomNumbe, roomNumbe)
                .eq(PlayRoom::getStreamPassword, password)
                .set(PlayRoom::getStatus, newStatus);
        if(StrUtil.isNotBlank(oldStatus)){
            lambdaUpdateWrapper.eq(PlayRoom::getStatus, oldStatus);
        }
        return this.update(lambdaUpdateWrapper);
    }

}