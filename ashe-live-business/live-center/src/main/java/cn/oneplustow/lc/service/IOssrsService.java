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
    int callBack(OssrsCallBackDto ossrsCallBackDto);

    boolean eliminateStream(String ip, Integer port, String clientId);

    OssrsSystemSummariesDto getOssrsServer(String ip, Integer port);

    boolean connectinoOssrsServer(String ip, Integer port);
}
