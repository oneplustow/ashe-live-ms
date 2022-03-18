package cn.oneplustow.mc.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.oneplustow.mc.service.ISysMessageService;
import cn.oneplustow.mc.service.handler.ISendMsgHandle;
import cn.oneplustow.mc.vo.SendMessageVo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

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
