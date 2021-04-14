package cn.oneplustow.live.service.impl;


import cn.hutool.core.util.StrUtil;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.live.entity.PlayRoom;
import cn.oneplustow.live.mapper.PlayRoomMapper;
import cn.oneplustow.live.service.IPlayRoomService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 直播间管理Service业务层处理
 * 
 * @author cc
 * @date 2021-04-14
 */
@Service
public class PlayRoomServiceImpl extends ServiceImpl<PlayRoomMapper, PlayRoom> implements IPlayRoomService
{

    @Override
    public List<PlayRoom> selectPage(PlayRoom playRoom) {
        PageUtil.startPage();
        QueryWrapper<PlayRoom> queryWrapper = new QueryWrapper<PlayRoom>();
        if (StrUtil.isNotBlank(playRoom.getName())) {
            queryWrapper.like("name", playRoom.getName());
        }
        if (playRoom.getUserId() != null) {
            queryWrapper.eq("user_id", playRoom.getUserId());
        }
        if (StrUtil.isNotBlank(playRoom.getSteamPassword())) {
            queryWrapper.eq("steam_password", playRoom.getSteamPassword());
        }
        if (playRoom.getViewNumber() != null) {
            queryWrapper.eq("view_number", playRoom.getViewNumber());
        }
        if (StrUtil.isNotBlank(playRoom.getStatus())) {
            queryWrapper.eq("status", playRoom.getStatus());
        }
        List<PlayRoom> list = this.list(queryWrapper);
        return list;
    }
}
