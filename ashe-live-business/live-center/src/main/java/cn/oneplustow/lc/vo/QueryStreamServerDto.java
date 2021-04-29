package cn.oneplustow.lc.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author cc
 */
@Data
public class QueryStreamServerDto implements Serializable {

    /** 流服务器ID */
    private Integer id;

    /** 流服务器名称 */
    private String serverName;

    /** 服务器IP */
    private String ip;

    /** 服务器端口 */
    private Integer port;

    /** 状态 */
    private String status;

}
