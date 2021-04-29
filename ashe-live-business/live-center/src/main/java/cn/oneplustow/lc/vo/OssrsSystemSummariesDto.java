package cn.oneplustow.lc.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
 * @author cc
 */
public class OssrsSystemSummariesDto {

    @JSONField(name = "conn_srs")
    private Integer connSrs;

    @JSONField(name = "conn_sys")
    private Integer connSys;

    @JSONField(name = "conn_sys_et")
    private Integer connSysEt;

    @JSONField(name = "conn_sys_tw")
    private Integer connSysTw;

    @JSONField(name = "conn_sys_udp")
    private Integer connSysUdp;

    /**cpu占用*/
    @JSONField(name = "cpu_percent")
    private BigDecimal cpuPercent;
}
