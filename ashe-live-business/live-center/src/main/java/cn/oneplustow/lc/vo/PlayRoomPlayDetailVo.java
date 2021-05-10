package cn.oneplustow.lc.vo;

import lombok.Data;

/**
 * @author cc
 */
@Data
public class PlayRoomPlayDetailVo {

    private Long id;

    /** 直播间号 */
    private String roomNumbe;

    /** 直播间名称 */
    private String name;

    /** 播放地址 */
    private String playStreamUrl;

    /** 观看人数 */
    private Long viewNumber;

    /** 直播间状态 */
    private String status;
}
