package cn.oneplustow.live.handler;

import cn.oneplustow.live.service.IPlayRoomService;
import cn.oneplustow.live.vo.OssrsCallBackDto;
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
    public int callBack(OssrsCallBackDto ossrsCallBackDto) {
        //相当于用户名
        String app = ossrsCallBackDto.getApp();
        //这里就是密码
        String stream = ossrsCallBackDto.getStream();
        Boolean success = iPlayRoomService.startPush(app,stream);
        if(!success){return FAILURE;}
        log.info(JSONObject.toJSONString(ossrsCallBackDto));
        return SUCCESS;
    }

    @Override
    public OssrsCallBackActionEnum handlerAction() {
        return OssrsCallBackActionEnum.on_publish;
    }
}
