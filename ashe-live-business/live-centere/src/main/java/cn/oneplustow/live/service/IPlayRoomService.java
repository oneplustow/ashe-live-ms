package cn.oneplustow.live.service;


import cn.oneplustow.live.entity.PlayRoom;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 直播间管理Service接口
 * 
 * @author cc
 * @date 2021-04-14
 */
public interface IPlayRoomService extends IService<PlayRoom>
{

    List<PlayRoom> selectPage(PlayRoom playRoom);
}
