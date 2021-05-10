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

    /**
     * 将分配记录修改为失效
     * @param playRoomId
     * @return
     */
    Boolean invalidRecord(Long playRoomId);

    /**
     * 通过直播间id 查询对应的正常状态的分配记录
     * @param playRoomId
     * @return
     */
    StreamServerAllotRecord getAllotRecordByPlayRoomId(Long playRoomId);

    /**
     * 进行流服务器的分配 并保存分配记录
     * @param playRoom
     * @return
     */
    StreamServerAllotRecord allotStreamServer(PlayRoom playRoom);
}
