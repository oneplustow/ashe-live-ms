package cn.oneplustow.live.vo;

import cn.oneplustow.live.handler.OssrsCallBackActionEnum;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author CC
 * @title: OssrsCallBackVo
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1622:47
 */
@Data
public class OssrsCallBackVo {
    /**事件*/
    private OssrsCallBackActionEnum action;

    /**客户端id*/
    @JsonAlias("client_id")
    private String clientId;

    /**连接ip*/
    private String ip;

    /**host名称*/
    private String vhost;

    /**app名称*/
    private String app;

    /**on_connect：连接到的流*/
    private String tcUrl;

    /**来源url*/
    private String pageUrl;

    /**on_close：发送字节*/
    @JsonAlias("send_bytes")
    private String sendBytes;

    /**on_close：接收字节*/
    @JsonAlias("recv_bytes")
    private String recvBytes;

    /**流名称*/
    private String stream;

    /**on_play：参数*/
    private String param;

    /**on_dvr：不知道啥*/
    private String cwd;

    /**on_dvr：文件路径*/
    private String file;

}
