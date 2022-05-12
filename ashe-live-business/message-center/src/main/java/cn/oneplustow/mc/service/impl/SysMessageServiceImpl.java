package cn.oneplustow.mc.service.impl;

import cn.hutool.extra.spring.SpringUtil;
import cn.oneplustow.mc.service.ISysMessageService;
import cn.oneplustow.mc.service.handler.ISendMsgHandle;
import cn.oneplustow.mc.entity.vo.SendMessageVo;
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
        String implClass = sendMessageVo.getEsType().getImplClass();
        ISendMsgHandle sendMsgHandle = (ISendMsgHandle) SpringUtil.getBean(implClass);
        if(sendMessageVo.getAsync() == null || sendMessageVo.getAsync()) {
           sendMsgHandle.sendMsg(sendMessageVo);
            return;
        }
        sendMsgHandle.sendMsg(sendMessageVo);
    }
}
