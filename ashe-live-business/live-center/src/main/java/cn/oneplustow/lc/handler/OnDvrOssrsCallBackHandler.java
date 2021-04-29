package cn.oneplustow.lc.handler;

import cn.oneplustow.lc.vo.OssrsCallBackDto;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 处理当DVR录制关闭一个flv文件时
 * on_dvr 事件
 * @author CC
 * @title: OnConnectionOssrsCallBackHandler
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/1623:09
 */
@Component
@Slf4j
public class OnDvrOssrsCallBackHandler implements OssrsCallBackHandler{
    @Override
    public boolean callBack(OssrsCallBackDto ossrsCallBackDto) {
        log.info(JSONObject.toJSONString(ossrsCallBackDto));
        return true;
    }

    @Override
    public OssrsCallBackActionEnum handlerAction() {
        return OssrsCallBackActionEnum.on_dvr;
    }
}
