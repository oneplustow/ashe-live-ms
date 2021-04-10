package cn.oneplustow.live.entity;

import cn.oneplustow.common.annoatation.Excel;
import cn.oneplustow.config.db.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 流服务器信息对象 live_stream_server
 * 
 * @author cc
 * @date 2021-04-09
 */
@Data
@TableName("live_stream_server")
public class StreamServer extends BaseEntity
{

    private static final long serialVersionUID = 1L;

    /** 流服务器ID */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 流服务器名称 */
    @Excel(name = "流服务器名称")
    private String serverName;

    /** 服务器IP */
    @Excel(name = "服务器IP")
    private String ip;

    /** 服务器端口 */
    @Excel(name = "服务器端口")
    private Integer port;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;



}
