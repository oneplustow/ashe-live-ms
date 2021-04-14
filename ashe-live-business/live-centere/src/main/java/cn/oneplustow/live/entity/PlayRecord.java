package cn.oneplustow.live.entity;

import cn.oneplustow.common.annoatation.Excel;
import cn.oneplustow.config.db.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 直播记录对象 live_play_record
 * 
 * @author cc
 * @date 2021-04-14
 */
@TableName("live_play_record")
@Data
public class PlayRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 直播历史id */
    private Long id;

    /** 直播间ID */
    private Long roomId;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDateTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDateTime;

    /** 状态 */
    @Excel(name = "状态")
    private String status;


}
