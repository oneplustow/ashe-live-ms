package cn.oneplustow.lc.service;


import cn.oneplustow.lc.entity.PlayRoom;
import cn.oneplustow.lc.entity.StreamServerAllotRecord;

/**
 * 直播间管理Service接口
 * 
 * @author cc
 * @date 2021-04-14
 */
public interface IStreamServerAllotRecordService {

    Boolean invalidRecord(Long playRoomId);

    StreamServerAllotRecord getAllotRecordByPlayRoomId(Long playRoomId);

    StreamServerAllotRecord allotStreamServer(PlayRoom playRomm);
}
