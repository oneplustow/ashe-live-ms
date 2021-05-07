package cn.oneplustow.lc.vo;

import lombok.Data;

/**
 * @author cc
 */
@Data
public class PlayRoomDetailVo {

    private Integer id;

    /** 直播间号 */
    private String roomNumbe;

    /** 直播间名称 */
    private String name;

    /** 直播间用户id */
    private Long userId;

    /** 直播间用户id */
    private String userName;

    /** 推流地址 */
    private String streamUrl;

    /** 推流密码 */
    private String streamPassword;

    /** 观看人数 */
    private Long viewNumber;

    /** 直播间状态 */
    private String status;
}
