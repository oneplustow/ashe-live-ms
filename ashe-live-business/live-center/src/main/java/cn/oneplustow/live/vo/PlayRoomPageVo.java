package cn.oneplustow.live.vo;

import cn.oneplustow.common.annoatation.Excel;
import lombok.Data;

/**
 * 直播间分页VO
 * @author cc
 */
@Data
public class PlayRoomPageVo {

    private Integer id;

    /** 直播间号 */
    private String roomNumbe;

    /** 直播间名称 */
    private String name;

    /** 直播间用户id */
    private Long userId;

    /** 直播间用户id */
    private String userName;

    /** 观看人数 */
    private Long viewNumber;

    /** 直播间状态 */
    private String status;
}
