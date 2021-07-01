package cn.oneplustow.lc.vo;

import cn.opl.annotation.Query;
import cn.opl.enums.QueryType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author cc
 */
@Data
public class QueryStreamServerQueryCriteria implements Serializable {

    /** 流服务器名称 */
    @Query(QueryType.LIKE)
    private String serverName;

    /** 服务器IP */
    @Query(QueryType.LIKE)
    private String ip;

    /** 服务器端口 */
    @Query
    private Integer port;

    /** 状态 */
    @Query
    private String status;

}
