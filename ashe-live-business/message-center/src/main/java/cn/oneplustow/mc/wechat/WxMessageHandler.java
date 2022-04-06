package cn.oneplustow.mc.wechat;

import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouterRule;

/**
 * Author lwj
 * Date 2022/4/6
 * Description TODO
 * BVersion 1.0
 **/
public interface WxMessageHandler extends WxMpMessageHandler {
    void setRule(WxMpMessageRouterRule rule);
}
