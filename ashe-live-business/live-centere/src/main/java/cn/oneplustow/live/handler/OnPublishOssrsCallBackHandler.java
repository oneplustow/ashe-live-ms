package cn.oneplustow.live.handler;

import cn.oneplustow.live.vo.OssrsCallBackVo;

/**
 * 处理当客户端发布流时，譬如flash/FMLE方式推流到服务器
 * on_publish 事件
 * @author CC
 * @title: OnConnectionOssrsCallBackHandler
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1623:09
 */
public class OnPublishOssrsCallBackHandler implements OssrsCallBackHandler{
    @Override
    public int callBack(OssrsCallBackVo ossrsCallBackVo) {
        return 0;
    }

    @Override
    public boolean handlerAction(OssrsCallBackActionEnum actionEnum) {
        return actionEnum == OssrsCallBackActionEnum.on_publish;
    }
}
