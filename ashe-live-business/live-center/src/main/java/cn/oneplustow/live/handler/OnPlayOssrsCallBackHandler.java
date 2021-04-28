package cn.oneplustow.live.handler;

import cn.oneplustow.live.service.IPlayRoomService;
import cn.oneplustow.live.vo.OssrsCallBackDto;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 处理当客户端开始播放流时
 * on_play 事件
 * @author CC
 * @title: OnConnectionOssrsCallBackHandler
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1623:09
 */
@Component
@Slf4j
public class OnPlayOssrsCallBackHandler implements OssrsCallBackHandler{

    @Autowired
    private IPlayRoomService iPlayRoomService;

    @Override
    public boolean callBack(OssrsCallBackDto ossrsCallBackDto) {
        String roomNumber = ossrsCallBackDto.getApp();
        log.info(JSONObject.toJSONString(ossrsCallBackDto));
        Boolean success = iPlayRoomService.viewPlay(roomNumber);
        return true;
    }

    @Override
    public OssrsCallBackActionEnum handlerAction() {
        return OssrsCallBackActionEnum.on_play;
    }
}
