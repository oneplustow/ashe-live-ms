package cn.oneplustow.live.service;


import cn.oneplustow.live.entity.PlayRoom;
import cn.oneplustow.live.vo.QueryPlayRoomDto;
import cn.oneplustow.live.vo.SavePlayRoomDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 直播间管理Service接口
 * 
 * @author cc
 * @date 2021-04-14
 */
public interface IPlayRoomService {

    /**
     * 查询直播间分页
     * @param playRoom
     * @return
     */
    List<PlayRoom> selectPage(QueryPlayRoomDto playRoom);

    /**
     * 观看直播接口
     * 会将观看人数加一
     * @param id
     * @return
     */
    Boolean viewPlay(String id);

    /**
     * 退出观看直播接口
     * 会将观看人数减一
     * @param app
     * @return
     */
    Boolean unViewPlay(String app);

    /**
     * 开始推流接口（同时带验证功能）
     * 会验证app和stream是否匹配
     * 会将直播间状态修改为正在直播状态
     * @param app
     * @param stream
     * @return 验证和修改成功返回true， 否则返回false
     */
    Boolean startPush(String app, String stream);

    /**
     * 停止推流接口
     * 会将直播间状态修改为等待推送状态
     * @param app
     * @param stream
     * @return
     */
    Boolean stopPush(String app, String stream);

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
     * @return
     */
    PlayRoom getPlayRoomById(Long id);

    /**
     * 开通直播间
     * @param name 直播间名称
     * @param userId 用户id
     * @return
     */
    boolean openUp(String name, Long userId);
}
