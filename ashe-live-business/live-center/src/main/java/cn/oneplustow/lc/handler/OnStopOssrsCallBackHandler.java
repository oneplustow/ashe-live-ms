package cn.oneplustow.lc.handler;

import cn.oneplustow.lc.service.IPlayRoomService;
import cn.oneplustow.lc.vo.OssrsCallBackDto;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 处理当客户端停止播放时。备注：停止播放可能不会关闭连接，还能再继续播放。
 * on_stop 事件
 * @author CC
 * @title: OnConnectionOssrsCallBackHandler
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1623:09
 */
@Component
@Slf4j
public class OnStopOssrsCallBackHandler implements OssrsCallBackHandler{

    @Autowired
    private IPlayRoomService iPlayRoomService;

    @Override
    public boolean callBack(OssrsCallBackDto ossrsCallBackDto) {
        log.info(JSONObject.toJSONString(ossrsCallBackDto));
        String app = ossrsCallBackDto.getApp();
        iPlayRoomService.unViewPlay(app);
        return true;
    }

    @Override
    public OssrsCallBackActionEnum handlerAction() {
        return OssrsCallBackActionEnum.on_stop;
    }
}
