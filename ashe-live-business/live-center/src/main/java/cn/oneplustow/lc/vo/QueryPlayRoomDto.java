package cn.oneplustow.lc.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author cc
 */
@Data
public class QueryPlayRoomDto {

    /** 直播间号 */
    private String roomNumbe;

    /** 直播间名称 */
    private String name;

    /** 直播间用户id */
    private Long userId;

    /** 直播间状态 */
    private String status;

    private Date startDateTime;

    private Date endDateTime;

}
