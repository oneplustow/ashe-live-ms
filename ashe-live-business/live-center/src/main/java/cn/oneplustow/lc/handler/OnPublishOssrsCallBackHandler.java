package cn.oneplustow.lc.handler;

import cn.oneplustow.lc.service.IPlayRoomService;
import cn.oneplustow.lc.vo.OssrsCallBackDto;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 处理当客户端发布流时，譬如flash/FMLE方式推流到服务器
 * on_publish 事件
 * @author CC
 * @title: OnConnectionOssrsCallBackHandler
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1623:09
 */
@Component
@Slf4j
public class OnPublishOssrsCallBackHandler implements OssrsCallBackHandler{

    @Autowired
    private IPlayRoomService iPlayRoomService;

    @Override
    public boolean callBack(OssrsCallBackDto ossrsCallBackDto) {
        log.info(JSONObject.toJSONString(ossrsCallBackDto));
        String appName = ossrsCallBackDto.getApp();
        //这里就是房间号
        String roomNumber = ossrsCallBackDto.getStream();
        //从参数里面获取密码
        String password = roomNumber + ossrsCallBackDto.getParam();
        //推流的客户端id
        String clientId = ossrsCallBackDto.getClientId();
        return iPlayRoomService.startPush(roomNumber,password,clientId);
    }

    @Override
    public OssrsCallBackActionEnum handlerAction() {
        return OssrsCallBackActionEnum.on_publish;
    }
}
