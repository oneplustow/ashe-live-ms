package cn.oneplustow.lc.entity;

import cn.oneplustow.config.db.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 直播间管理对象 live_play_room
 *
 * @author cc
 * @date 2021-04-14
 */
@Data
@TableName("lc_play_room")
public class PlayRoom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 直播间号 */
    private String roomNumbe;

    /** 直播间名称 */
    private String name;

    /** 直播间用户id */
    private Long userId;

    /** 观看人数 */
    private Long viewNumber;

    /** 客户端id */
    private String clientId;

    /** 直播间状态 */
    private String status;


}
