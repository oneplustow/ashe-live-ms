package cn.oneplustow.live.entity;

import cn.oneplustow.common.annoatation.Excel;
import cn.oneplustow.config.db.model.SysBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 直播间管理对象 live_play_room
 * 
 * @author cc
 * @date 2021-04-14
 */
@TableName("live_play_room")
@Data
public class PlayRoom extends SysBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 直播间ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 直播间名称 */
    @Excel(name = "直播间号")
    private String roomNumbe;

    /** 直播间名称 */
    @Excel(name = "直播间名称")
    private String name;

    /** 直播间用户id */
    @Excel(name = "直播间用户id")
    private Long userId;

    /** 推流地址 */
    private String steamUrl;

    /** 推流app */
    private String steamApp;

    /** 推流密码 */
    @Excel(name = "推流密码")
    private String steamPassword;

    /** 观看人数 */
    @Excel(name = "观看人数")
    private Long viewNumber;

    /** 直播间状态 */
    @Excel(name = "直播间状态")
    private String status;


}
