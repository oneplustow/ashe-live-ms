package cn.oneplustow.live.handler;

import cn.oneplustow.live.service.IPlayRoomService;
import cn.oneplustow.live.vo.OssrsCallBackDto;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 处理当客户端停止发布流时
 * on_unpublish 事件
 * @author CC
 * @title: OnConnectionOssrsCallBackHandler
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1623:09
 */
@Component
@Slf4j
public class OnUnpublishOssrsCallBackHandler implements OssrsCallBackHandler{

    @Autowired
    private IPlayRoomService iPlayRoomService;

    @Override
    public int callBack(OssrsCallBackDto ossrsCallBackDto) {
        String app = ossrsCallBackDto.getApp();
        String stream = ossrsCallBackDto.getStream();
        iPlayRoomService.stopPush(app,stream);
        return 0;
    }

    @Override
    public OssrsCallBackActionEnum handlerAction() {
        return OssrsCallBackActionEnum.on_unpublish;
    }
}
