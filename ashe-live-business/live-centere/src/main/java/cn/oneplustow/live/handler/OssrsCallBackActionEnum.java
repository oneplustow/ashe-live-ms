package cn.oneplustow.live.handler;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.ToString;

/**
 * @author CC
 * @title: OssrsCallBackAction
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1623:04
 */
public enum OssrsCallBackActionEnum {
    /**当客户端连接到指定的vhost和app时*/
    on_connect,
    /**当客户端关闭连接，或者SRS主动关闭连接时*/
    on_close,
    /**当客户端发布流时，譬如flash/FMLE方式推流到服务器*/
    on_publish,
    /**当客户端停止发布流时*/
    on_unpublish,
    /**当客户端开始播放流时*/
    on_play,
    /**当客户端停止播放时。备注：停止播放可能不会关闭连接，还能再继续播放。*/
    on_stop,
    /**当DVR录制关闭一个flv文件时*/
    on_dvr;

}
