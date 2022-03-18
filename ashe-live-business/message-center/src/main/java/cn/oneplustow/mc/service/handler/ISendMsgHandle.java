package cn.oneplustow.mc.service.handler;

import cn.oneplustow.mc.vo.SendMessageVo;

/**
 * Author lwj
 * Date 2022/3/18
 * Description TODO
 * BVersion 1.0
 **/
public interface ISendMsgHandle {

    /**
     *
     * @param sendMessageVo
     */
    void sendMsg(SendMessageVo sendMessageVo);
}
