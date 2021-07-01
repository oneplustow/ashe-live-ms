package cn.oneplustow.lc.handler;

import cn.oneplustow.lc.vo.OssrsCallBackDto;

/**
 * @author CC
 * @title: OssrsCallBackHandler
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1623:03
 */
public interface OssrsCallBackHandler {



    /**
     * 执行具体的回调事件
     * @param ossrsCallBackDto 回调实体数据
     * @return 0代表成功 其他代表失败
     */
    boolean callBack(OssrsCallBackDto ossrsCallBackDto);

    /**
     * 判断当前是否可以处理当前回调事件
     * @param actionEnum 回调事件
     * @return 是否
     */
    OssrsCallBackActionEnum handlerAction();
}
