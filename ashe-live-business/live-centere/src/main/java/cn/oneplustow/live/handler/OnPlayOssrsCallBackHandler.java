package cn.oneplustow.live.handler;

import cn.oneplustow.live.vo.OssrsCallBackVo;

/**
 * 处理当客户端开始播放流时
 * on_play 事件
 * @author CC
 * @title: OnConnectionOssrsCallBackHandler
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1623:09
 */
public class OnPlayOssrsCallBackHandler implements OssrsCallBackHandler{
    @Override
    public int callBack(OssrsCallBackVo ossrsCallBackVo) {
        return 0;
    }

    @Override
    public boolean handlerAction(OssrsCallBackActionEnum actionEnum) {
        return actionEnum == OssrsCallBackActionEnum.on_play;
    }
}
