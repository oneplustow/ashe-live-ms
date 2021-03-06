package cn.oneplustow.lc.service.impl;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.lc.config.RandomCodeGenerator;
import cn.oneplustow.lc.entity.PlayRoom;
import cn.oneplustow.lc.entity.StreamServer;
import cn.oneplustow.lc.entity.StreamServerAllotRecord;
import cn.oneplustow.lc.mapper.PlayRoomMapper;
import cn.oneplustow.lc.mapstruct.PlayRoom2PlayRoomDetailVo;
import cn.oneplustow.lc.service.IOssrsService;
import cn.oneplustow.lc.service.IPlayRoomService;
import cn.oneplustow.lc.service.IStreamServerAllotRecordService;
import cn.oneplustow.lc.vo.*;
import cn.opl.generate.QueryUtil;
import cn.opl.mapstruct.MapStructContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private IStreamServerAllotRecordService allotRecordService;
    @Autowired
    private MapStructContext mapStructContext;

    @Autowired
    private IOssrsService ossrsService;

    @Autowired
    private RandomCodeGenerator randomCodeGenerator;

    @Override
    public List<PlayRoomPageVo> selectStartStatusPage(String roomNumbe, String roomName) {
        PlayRoomQueryCriteria playRoomQueryCriteria = new PlayRoomQueryCriteria();
        playRoomQueryCriteria.setStatus(START);
        playRoomQueryCriteria.setRoomNumbe(roomNumbe);
        playRoomQueryCriteria.setName(roomName);
        return selectPage(playRoomQueryCriteria);
    }

    @Override
    public List<PlayRoomPageVo> selectPage(PlayRoomQueryCriteria playRoom) {
        PageUtil.startPage();
        QueryWrapper<PlayRoom> queryWrapper = QueryUtil.generate(playRoom);
        List<PlayRoom> playRoomList = this.list(queryWrapper);
        List<PlayRoomPageVo> playRoomPageVoList = mapStructContext.conver(playRoomList, PlayRoomPageVo.class);
        return playRoomPageVoList;
    }


    @Override
    public Boolean delPlayRoom(List<Long> idList) {
        return this.removeByIds(idList);
    }

    @Override
    public Boolean savePlayRoom(SavePlayRoomDto savePlayRoomDto) {
        PlayRoom playRoom = mapStructContext.conver(savePlayRoomDto, PlayRoom.class);
        if(playRoom.getId() == null){
            initPlayRoom(playRoom);
            this.save(playRoom);
            //保存成功后根据ID 生成随机码
            playRoom.setRoomNumbe(randomCodeGenerator.gengerCode(playRoom.getId(),7));
        }
        return this.updateById(playRoom);
    }

    private void initPlayRoom(PlayRoom playRoom){
        Long userId = playRoom.getUserId();
        String name = playRoom.getName();
        Assert.isTrue(StrUtil.isNotBlank(name),"直播间房间名不能为空");
        int userCount = this.count(new LambdaQueryWrapper<PlayRoom>().eq(PlayRoom::getUserId, userId));
        Assert.isTrue(userCount == 0,"当前用户已经拥有直播间");
        int nameCount = this.count(new LambdaQueryWrapper<PlayRoom>().eq(PlayRoom::getName, playRoom.getName()));
        Assert.isTrue(nameCount == 0,"直播间名称已被占用");
//        if(StrUtil.isBlank(playRoom.getRoomNumbe())) {
//            //todo 直播间名称可以有一定规律进行生产，后续加上序列号生产模块
//            playRoom.setRoomNumbe(IdUtil.simpleUUID());
//        }
    }

    private PlayRoomDetailVo getPlayRoomDetailVo(PlayRoom playRoom){
        if(playRoom == null){
            throw new WarningMessageException("直播间不存在");
        }
        StreamServerAllotRecord allotRecord = allotRecordService.getAllotRecordByPlayRoomId(playRoom.getId());
        PlayRoom2PlayRoomDetailVo iMapStruct = (PlayRoom2PlayRoomDetailVo)mapStructContext.getIMapStruct(playRoom.getClass(), PlayRoomDetailVo.class);
        PlayRoomDetailVo playRoomDetailVo = iMapStruct.convers(playRoom, allotRecord);
        return playRoomDetailVo;
    }

    @Override
    public PlayRoomDetailVo getPlayRoomDetailVoByIdOrUserId(Long id, Long userId) {
        PlayRoom playRoom = getPlayRoomByIdOrUserId(id,userId);
        return getPlayRoomDetailVo(playRoom);
    }

    @Override
    public PlayRoomPlayDetailVo getPlayRoomPlayDetailVo(Long id,String nameOrNum,String protocol) {
        if(StrUtil.isBlank(nameOrNum) && id == null){
            throw new WarningMessageException("请输入查询条件");
        }
        LambdaQueryWrapper<PlayRoom> wrapper = new LambdaQueryWrapper<PlayRoom>();
        if(id != null){
            wrapper.eq(PlayRoom::getId,id);
        }
        if(StrUtil.isNotBlank(nameOrNum)){
            wrapper.eq(PlayRoom::getName, nameOrNum).or().eq(PlayRoom::getRoomNumbe, nameOrNum);
        }
        PlayRoom playRoom = this.getOne(wrapper);
        if(playRoom == null){
            throw new WarningMessageException("直播间不存在");
        }
        if(!StrUtil.equals(playRoom.getStatus(), START)){
            throw new WarningMessageException("直播间还未开播");
        }
        PlayRoomPlayDetailVo detailVo = mapStructContext.conver(getPlayRoomDetailVo(playRoom), PlayRoomPlayDetailVo.class);
        //拼接具体协议
        detailVo.setPlayStreamUrl(protocol + detailVo.getPlayStreamUrl());
        return detailVo;
    }

    private PlayRoom getPlayRoomByIdOrUserId(Long id, Long userId) {
        LambdaQueryWrapper<PlayRoom> wrapper = new LambdaQueryWrapper<>();
        if(id != null){wrapper.eq(PlayRoom::getId,id);}
        if(userId != null){wrapper.eq(PlayRoom::getUserId,userId);}
        return this.getOne(wrapper);
    }

    @Override
    public boolean openUp(String name, Long userId) {
        SavePlayRoomDto savePlayRoomDto = new SavePlayRoomDto();
        savePlayRoomDto.setName(name);
        savePlayRoomDto.setUserId(userId);
        return this.savePlayRoom(savePlayRoomDto);
    }

    @Override
    public Boolean isOpenPlayRoom(Long userId) {
        PlayRoom playRoom = getPlayRoomByIdOrUserId(null, userId);
        return playRoom != null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PlayRoomDetailVo startLive(Long userId) {
        PlayRoom playRoom = getPlayRoomByIdOrUserId(null, userId);
        Assert.notNull(playRoom,"无法找到您的直播间");
        Assert.isFalse(playRoom.getStatus() == WAIT_PUSH,"您已经在直播中");
        Assert.isFalse(playRoom.getStatus() == START,"您已经在直播中");
        playRoom.setStatus(WAIT_PUSH);
        this.updateById(playRoom);
        PlayRoomDetailVo playRoomDetailVo = mapStructContext.conver(playRoom, PlayRoomDetailVo.class);
        StreamServerAllotRecord streamServerAllotRecord = allotRecordService.allotStreamServer(playRoom);
        playRoomDetailVo.setPushStreamUrl(streamServerAllotRecord.getPushStreamUrl());
        playRoomDetailVo.setPushStreamPassword(streamServerAllotRecord.getPushStreamPassword());
        return playRoomDetailVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PlayRoomDetailVo stopLive(Long userId) {
        PlayRoom playRoom = getPlayRoomByIdOrUserId(null, userId);
        Assert.notNull(playRoom,"无法找到您的直播间");
        Assert.isFalse(playRoom.getStatus() == NOT_START,"您已经是关闭状态");
        playRoom.setStatus(NOT_START);
        this.updateById(playRoom);
        allotRecordService.invalidRecord(playRoom.getId());
        PlayRoomDetailVo playRoomDetailVo = mapStructContext.conver(playRoom, PlayRoomDetailVo.class);
        return playRoomDetailVo;
    }

    @Async
    @Override
    public Boolean viewPlay(String roomNumber){
        return updateViewNumber(roomNumber,1);
    }

    @Async
    @Override
    public Boolean unViewPlay(String id) {
        return updateViewNumber(id,-1);
    }

    /**
     * 更新观看数量
     * @param roomNumber
     * @param count
     * @return
     */
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
            StreamServer streamServer = allotRecordService.getAllotStreamServer(playRoom.getId());
            //踢流
            ossrsService.eliminateStream(streamServer.getIp(),streamServer.getPort(),clientId);
        }
        playRoom.setStatus(START);
        playRoom.setClientId(clientId);
        return this.updateById(playRoom);
    }

    @Override
    public Boolean stopPush(String roomNumbe, String password) {
        return updateStatusByAppAndApssword(roomNumbe,password,null,WAIT_PUSH);
    }

    private PlayRoom getPlayRoomByAppAndPassword(String roomNumbe,String password){
        PlayRoom playRoom = this.getOne(new LambdaQueryWrapper<PlayRoom>()
                .eq(PlayRoom::getRoomNumbe, roomNumbe));
        if(playRoom == null ){return null;}
        StreamServerAllotRecord recordByPlayRoomId = allotRecordService.getAllotRecordByPlayRoomId(playRoom.getId());
        if (StrUtil.equals(password,recordByPlayRoomId.getPushStreamPassword())) {
            return playRoom;
        }
        return null;

    }

    private Boolean updateStatusByAppAndApssword(String roomNumbe,String password,String oldStatus,String newStatus){
        PlayRoom playRoom = getPlayRoomByAppAndPassword(roomNumbe, password);
        if(playRoom == null){return false;}
        return updateStatus(playRoom.getId(),oldStatus,newStatus);
    }

    private Boolean updateStatus(Long id,String oldStatus,String newStatus){
        LambdaUpdateWrapper<PlayRoom> lambdaUpdateWrapper = new LambdaUpdateWrapper<PlayRoom>()
                .eq(PlayRoom::getId, id)
                .set(PlayRoom::getStatus, newStatus);
        if(StrUtil.isNotBlank(oldStatus)){
            lambdaUpdateWrapper.eq(PlayRoom::getStatus, oldStatus);
        }
        return this.update(lambdaUpdateWrapper);
    }
}
