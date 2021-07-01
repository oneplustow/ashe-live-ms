package cn.oneplustow.lc.service;

import cn.oneplustow.lc.vo.OssrsCallBackDto;
import cn.oneplustow.lc.vo.OssrsSystemSummariesDto;

/**
 * @author CC
 * @title: IOssrsService
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1622:47
 */
public interface IOssrsService {
    /**
     * oss回调接口
     * @param ossrsCallBackDto
     * @return
     */
    int callBack(OssrsCallBackDto ossrsCallBackDto);

    /**
     * 提流 踢掉一个指定的客户端流
     * @param ip
     * @param port
     * @param clientId
     * @return
     */
    boolean eliminateStream(String ip, Integer port, String clientId);

    /**
     * 通过ip和端口获取指定流服务器的信息
     * @param ip
     * @param port
     * @return
     */
    OssrsSystemSummariesDto getOssrsServer(String ip, Integer port);

    /**
     * 连接指定的服务器，返回是否连接成功
     * @param ip
     * @param port
     * @return
     */
    boolean connectinoOssrsServer(String ip, Integer port);
}
