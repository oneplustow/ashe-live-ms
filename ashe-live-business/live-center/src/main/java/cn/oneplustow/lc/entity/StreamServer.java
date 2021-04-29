package cn.oneplustow.lc.entity;

import cn.oneplustow.common.annoatation.Excel;
import cn.oneplustow.config.db.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 流服务器信息对象 live_stream_server
 * 
 * @author cc
 * @date 2021-04-09
 */
@Data
@TableName("lc_stream_server")
public class StreamServer extends BaseEntity
{

    private static final long serialVersionUID = 1L;


    /** 流服务器名称 */
    @Excel(name = "流服务器名称")
    private String serverName;

    /** 服务器IP */
    @Excel(name = "服务器IP")
    private String ip;

    /** 服务器端口 */
    @Excel(name = "服务器端口")
    private Integer port;

    /** cpu占用 */
    private Integer cpuUse;

    /** 推流数量 */
    private Integer pushSteamCount;

    /** 拉流数量 */
    private Integer pullSteamCount;

    /** 状态<p/>
     * {@link cn.oneplustow.common.constant.DbConstants.SteamServerStatus}
     */
    private String status;



}
