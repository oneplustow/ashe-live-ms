package cn.oneplustow.live.handler;

import cn.oneplustow.live.service.IPlayRoomService;
import cn.oneplustow.live.vo.OssrsCallBackDto;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 处理当客户端关闭连接，或者SRS主动关闭连接时
 * on_close 事件
 * @author CC
 * @title: OnConnectionOssrsCallBackHandler
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1623:09
 */
@Component
@Slf4j
public class OnCloseOssrsCallBackHandler implements OssrsCallBackHandler{

    @Autowired
    private IPlayRoomService iPlayRoomService;

    @Override
    public boolean callBack(OssrsCallBackDto ossrsCallBackDto) {
        log.info(JSONObject.toJSONString(ossrsCallBackDto));
        String roomNumber = ossrsCallBackDto.getApp();
        String password = ossrsCallBackDto.getStream();
        iPlayRoomService.stopPush(roomNumber,password);
        return true;
    }

    @Override
    public OssrsCallBackActionEnum handlerAction() {
        return OssrsCallBackActionEnum.on_close;
    }
}
