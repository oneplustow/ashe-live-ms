package cn.oneplustow.lc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cc
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@TableName("lc_stream_server_allot_record")
public class StreamServerAllotRecord implements Serializable {
    private Long id;
    /**流服务器id*/
    private Long streamServerId;
    /**直播间id*/
    private Long playRoomId;
    /**推流url*/
    private String pushStreamUrl;
    /**播放流url*/
    private String playStreamUrl;
    /**推流参数*/
    private String pushStreamParam;
    /**推流密码*/
    private String pushStreamPassword;
    /**状态*/
    private String status;
    /**创建时间*/
    private Date createTime;
}
