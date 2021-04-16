package cn.oneplustow.live.handler;

import cn.oneplustow.live.vo.OssrsCallBackVo;

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
     * @param ossrsCallBackVo 回调实体数据
     * @return 0代表成功 其他代表失败
     */
    int callBack(OssrsCallBackVo ossrsCallBackVo);

    /**
     * 判断当前是否可以处理当前回调事件
     * @param actionEnum 回调事件
     * @return 是否
     */
    boolean handlerAction(OssrsCallBackActionEnum actionEnum);
}
