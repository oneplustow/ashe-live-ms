package cn.oneplustow.live.handler;

import cn.oneplustow.live.vo.OssrsCallBackVo;

/**
 * 处理当客户端关闭连接，或者SRS主动关闭连接时
 * on_close 事件
 * @author CC
 * @title: OnConnectionOssrsCallBackHandler
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1623:09
 */
public class OnCloseOssrsCallBackHandler implements OssrsCallBackHandler{
    @Override
    public int callBack(OssrsCallBackVo ossrsCallBackVo) {
        return 0;
    }

    @Override
    public boolean handlerAction(OssrsCallBackActionEnum actionEnum) {
        return actionEnum == OssrsCallBackActionEnum.on_close;
    }
}
