package cn.oneplustow.live.vo;

import cn.oneplustow.common.annoatation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author cc
 */
@Data
public class SavePlayRoomDto implements Serializable {

    /** 直播间ID */
    private Long id;

    /** 直播间号 */
    private String roomNumbe;

    /** 直播间名称 */
    private String name;

    /** 直播间用户id */
    private Long userId;

    /** 推流密码 */
    private String steamPassword;
}
