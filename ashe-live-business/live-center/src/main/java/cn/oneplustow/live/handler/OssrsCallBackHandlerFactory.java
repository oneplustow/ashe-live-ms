package cn.oneplustow.live.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author CC
 * @title: OssrsCallBackHandlerFactory
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/2022:19
 */
@Component
public class OssrsCallBackHandlerFactory implements ApplicationRunner {

    private List<OssrsCallBackHandler> ossrsCallBackHandlers;

    private ConcurrentHashMap<OssrsCallBackActionEnum,OssrsCallBackHandler> cacheMap = new ConcurrentHashMap<>(10);

    @Autowired
    public void setOssrsCallBackHandlers(List<OssrsCallBackHandler> ossrsCallBackHandlers) {
        this.ossrsCallBackHandlers = ossrsCallBackHandlers;
    }

    public OssrsCallBackHandler getOssrsCallBackHandler(OssrsCallBackActionEnum actionEnum){
        return cacheMap.get(actionEnum);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (OssrsCallBackHandler ossrsCallBackHandler : ossrsCallBackHandlers) {
            cacheMap.put(ossrsCallBackHandler.handlerAction(),ossrsCallBackHandler);
        }
    }
}
