package cn.oneplustow.mc.service;

import cn.oneplustow.mc.vo.SendMessageVo;

/**
 * Author lwj
 * Date 2022/3/16
 * Description TODO
 * BVersion 1.0
 **/
public interface ISysMessageService {
    void sendMessage(SendMessageVo sendMessageVo);

    String sendVerifyCode(String receive, String configKey);
}
