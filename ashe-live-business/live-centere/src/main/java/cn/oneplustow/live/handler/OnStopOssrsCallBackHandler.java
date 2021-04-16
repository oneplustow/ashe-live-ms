package cn.oneplustow.live.handler;

import cn.oneplustow.live.vo.OssrsCallBackVo;

/**
 * 处理当客户端停止播放时。备注：停止播放可能不会关闭连接，还能再继续播放。
 * on_stop 事件
 * @author CC
 * @title: OnConnectionOssrsCallBackHandler
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1623:09
 */
public class OnStopOssrsCallBackHandler implements OssrsCallBackHandler{
    @Override
    public int callBack(OssrsCallBackVo ossrsCallBackVo) {
        return 0;
    }

    @Override
    public boolean handlerAction(OssrsCallBackActionEnum actionEnum) {
        return actionEnum == OssrsCallBackActionEnum.on_stop;
    }
}
