package cn.oneplustow.live.service.impl;

import cn.oneplustow.live.service.IOssrsService;
import cn.oneplustow.live.vo.OssrsCallBackVo;

/**
 * @author CC
 * @title: OssrsServiceImpl
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1622:47
 */
public class OssrsServiceImpl implements IOssrsService {
    /**成功的情况下返回给ossrs服务器的*/
    private int success = 0;
    /**失败情况下返回给ossrs服务器*/
    private int fail = 1;

    @Override
    public int callBack(OssrsCallBackVo ossrsCallBackVo) {


        return success;
    }
}
