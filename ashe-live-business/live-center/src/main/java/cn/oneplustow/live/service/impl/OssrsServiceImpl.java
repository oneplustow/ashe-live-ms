package cn.oneplustow.live.service.impl;

import cn.oneplustow.live.handler.OssrsCallBackHandler;
import cn.oneplustow.live.handler.OssrsCallBackHandlerFactory;
import cn.oneplustow.live.service.IOssrsService;
import cn.oneplustow.live.vo.OssrsCallBackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CC
 * @title: OssrsServiceImpl
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1622:47
 */
@Service("ossrsService")
public class OssrsServiceImpl implements IOssrsService {
    /**成功的情况下返回给ossrs服务器的*/
    private static final int SUCCESS = 0;
    /**失败情况下返回给ossrs服务器*/
    private static final int FAIL = 1;

    @Autowired
    private OssrsCallBackHandlerFactory callBackHandlerFactory;

    @Override
    public int callBack(OssrsCallBackDto ossrsCallBackDto) {
        OssrsCallBackHandler ossrsCallBackHandler = callBackHandlerFactory.getOssrsCallBackHandler(ossrsCallBackDto.getAction());
        return ossrsCallBackHandler.callBack(ossrsCallBackDto) ? SUCCESS : FAIL;
    }
}
