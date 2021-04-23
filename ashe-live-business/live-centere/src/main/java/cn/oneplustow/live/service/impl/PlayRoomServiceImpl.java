package cn.oneplustow.live.service.impl;


import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.constant.Constants;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.common.mapstruct.MapStructContext;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.live.entity.PlayRoom;
import cn.oneplustow.live.entity.StreamServer;
import cn.oneplustow.live.mapper.PlayRoomMapper;
import cn.oneplustow.live.service.IPlayRoomService;
import cn.oneplustow.live.service.IStreamServerService;
import cn.oneplustow.live.vo.QueryPlayRoomDto;
import cn.oneplustow.live.vo.SavePlayRoomDto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static cn.oneplustow.common.constant.DbConstants.PalyRoomStatus.*;

/**
 * 直播间管理Service业务层处理
 * 
 * @author cc
 * @date 2021-04-14
 */
@Service
public class PlayRoomServiceImpl extends ServiceImpl<PlayRoomMapper, PlayRoom> implements IPlayRoomService
{
    private final String streamTemplate = "rtmp:{}:{}/{}";
    @Autowired
    private IStreamServerService streamServerService;

    @Autowired
    private MapStructContext mapStructContext;

    @Override
    public Boolean delPlayRoom(List<Long> idList) {
        return this.removeByIds(idList);
    }

    @Override
    public Boolean savePlayRoom(SavePlayRoomDto savePlayRoomDto) {
        PlayRoom playRoom = mapStructContext.conver(savePlayRoomDto, PlayRoom.class);

        if(playRoom.getId() == null){
            String roomNumber = IdUtil.simpleUUID();
            if(StrUtil.isBlank(playRoom.getRoomNumbe())) {
                playRoom.setRoomNumbe(roomNumber);
            }
            playRoom.setSteamApp(playRoom.getRoomNumbe());
            playRoom.setSteamPassword(playRoom.getRoomNumbe());
            StreamServer available = streamServerService.getAvailable();
            String stream = StrUtil.format(streamTemplate, available.getIp(), available.getPort(), playRoom.getSteamApp());
            playRoom.setSteamUrl(stream);
            return this.save(playRoom);
        }
        return this.updateById(playRoom);
    }

    @Override
    public PlayRoom getPlayRoomById(Long id) {
        return getById(id);
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
    public Boolean viewPlay(String id){
        return updateViewNumber(id,1);
    }
    @Override
    public Boolean unViewPlay(String id) {
        return updateViewNumber(id,-1);
    }

    private Boolean updateViewNumber(String id, Integer count){
        while (true) {
            //1 查询出现在的观看数量
            PlayRoom playRoom = this.getById(id);
            if (playRoom == null) {
                throw new WarningMessageException("ID不存在");
            }
            //2 增加观看数量
            Long clickCount = playRoom.getViewNumber();
            Long newClickCount = clickCount + count;
            //3 用cas更新到数据库
            boolean success = this.update(new LambdaUpdateWrapper<PlayRoom>()
                    .set(PlayRoom::getViewNumber, newClickCount)
                    .eq(PlayRoom::getId, id)
                    .eq(PlayRoom::getViewNumber, clickCount));
            if(success){return true;}
        }
    }


    @Override
    public Boolean startPush(String app, String stream) {
        return updateStatusByAppAndApssword(app,stream,WAIT_PUSH,START);
    }

    @Override
    public Boolean stopPush(String app, String stream) {
        return updateStatusByAppAndApssword(app,stream,START,WAIT_PUSH);
    }

    private Boolean updateStatusByAppAndApssword(String app,String password,String oldStatus,String newStatus){
        return this.update(new LambdaUpdateWrapper<PlayRoom>()
                .eq(PlayRoom::getStatus, oldStatus)
                .eq(PlayRoom::getSteamApp, app)
                .eq(PlayRoom::getSteamPassword, password)
                .set(PlayRoom::getStatus,newStatus));
    }

}
