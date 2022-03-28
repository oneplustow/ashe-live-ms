package cn.oneplustow.mc.service.handler;

import cn.hutool.core.collection.CollUtil;
import cn.oneplustow.api.sc.model.SimpleUser;
import cn.oneplustow.api.sc.service.UserFeginApi;
import cn.oneplustow.mc.entity.Message;
import cn.oneplustow.mc.vo.SendMessageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lwj
 */
@Slf4j
public abstract class BaseSendMsgHandle implements ISendMsgHandle {
    @Autowired
    private UserFeginApi sysUserService;

    @Override
    public void sendMsg(SendMessageVo sendMessageVo) {
        //将用户id转换为具体的可送发对象
        Collection<Long> userIdList = sendMessageVo.getEsUserId();
        List<String> esReceiver = sendMessageVo.getEsReceiver();

        if(CollUtil.isNotEmpty(userIdList)){
            Set<String> collect = userIdList.stream().map(String::valueOf).collect(Collectors.toSet());
            List<SimpleUser> simpleUsers = sysUserService.getSimpleUserListById(collect);
            esReceiver = simpleUsers.stream().map(this::getReceiver).collect(Collectors.toList());
        }

        for (String receiver : esReceiver) {
             doSendMsg(receiver,sendMessageVo);
        }
        //保存推送记录
        //save(resultList);
    }


    abstract String getReceiver(SimpleUser user);

    abstract Message doSendMsg(String esReceiver, SendMessageVo sendMessageVo);
}
