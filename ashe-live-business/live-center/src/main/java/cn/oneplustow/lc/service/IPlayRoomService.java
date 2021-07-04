package cn.oneplustow.lc.service;


import cn.oneplustow.lc.vo.*;

import java.util.List;

/**
 * 直播间管理Service接口
 * 
 * @author cc
 * @date 2021-04-14
 */
public interface IPlayRoomService {

    /**
     * 查询开始直播状态的直播间
     * @return
     */
    List<PlayRoomPageVo> selectStartStatusPage(String roomNumbe,String roomName);

    /**
     * 查询直播间分页
     * @param playRoom
     * @return
     */
    List<PlayRoomPageVo> selectPage(PlayRoomQueryCriteria playRoom);

    /**
     * 观看直播接口
     * 会将观看人数加一
     * @param roomNumber 房间号
     * @return
     */
    Boolean viewPlay(String roomNumber);

    /**
     * 退出观看直播接口
     * 会将观看人数减一
     * @param roomNumbe 房间号
     * @return
     */
    Boolean unViewPlay(String roomNumbe);

    /**
     * 开始推流接口（同时带验证功能）
     * 会验证app和stream是否匹配
     * 会将直播间状态修改为正在直播状态
     * @param roomNumbe
     * @param password
     * @param clientId
     * @return 验证和修改成功返回true， 否则返回false
     */
    Boolean startPush(String roomNumbe, String password,String clientId);

    /**
     * 停止推流接口
     * 会将直播间状态修改为等待推送状态
     * @param roomNumbe
     * @param password
     * @return
     */
    Boolean stopPush(String roomNumbe, String password);

    /**
     * 删除直播间
     * @param idList
     * @return
     */
    Boolean delPlayRoom(List<Long> idList);

    /**
     * 保存直播间
     * 可以修改和新增
     * @param savePlayRoomDto
     * @return
     */
    Boolean savePlayRoom(SavePlayRoomDto savePlayRoomDto);

    /**
     * 获取直播间详情
     * @param id
     * @param userId
     * @return
     */
    PlayRoomDetailVo getPlayRoomDetailVoByIdOrUserId(Long id, Long userId);
    /**
     * 获取直播间详情
     * @param id
     * @param userId
     * @return
     */
    PlayRoomPlayDetailVo getPlayRoomPlayDetailVoById(Long id);

    /**
     * 开通直播间
     * @param name 直播间名称
     * @param userId 用户id
     * @return
     */
    boolean openUp(String name, Long userId);

    /**
     * 开始直播
     * @param userId
     * @return
     */
    PlayRoomDetailVo startLive(Long userId);

    /**
     * 关闭直播
     * @param userId
     * @return
     */
    PlayRoomDetailVo stopLive(Long userId);

    /**
     * 用户是否开通直播间
     * @param userId 用户id
     * @return
     */
    Boolean isOpenPlayRoom(Long userId);
}
