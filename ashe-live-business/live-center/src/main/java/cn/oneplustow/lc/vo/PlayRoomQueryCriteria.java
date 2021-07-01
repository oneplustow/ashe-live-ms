package cn.oneplustow.lc.vo;

import cn.opl.annotation.Query;
import cn.opl.enums.QueryType;
import lombok.Data;

import java.util.Date;

/**
 * @author cc
 */
@Data
public class PlayRoomQueryCriteria {

    /** 直播间号 */
    @Query
    private String roomNumbe;

    /** 直播间名称 */
    @Query(QueryType.LIKE)
    private String name;

    /** 直播间用户id */
    @Query
    private Long userId;

    /** 直播间状态 */
    @Query
    private String status;

    @Query(value = QueryType.GT_EQ,fieldName = "create_time")
    private Date startDateTime;

    @Query(value = QueryType.LT,fieldName = "create_time")
    private Date endDateTime;

}
