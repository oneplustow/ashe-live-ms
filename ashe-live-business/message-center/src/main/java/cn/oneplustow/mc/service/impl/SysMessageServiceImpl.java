package cn.oneplustow.mc.service.impl;

import cn.oneplustow.mc.service.ISysMessageService;
import cn.oneplustow.mc.vo.SendMessageVo;
import org.springframework.stereotype.Component;

/**
 * Author lwj
 * Date 2022/3/16
 * Description TODO
 * BVersion 1.0
 **/
@Component
public class SysMessageServiceImpl implements ISysMessageService {
    @Override
    public void sendMessage(SendMessageVo sendMessageVo) {

    }

    @Override
    public String sendVerifyCode(String receive, String configKey) {
        return null;
    }
}
