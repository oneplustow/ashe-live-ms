package cn.oneplustow.lc.handler;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author CC
 * @title: OssrsCallBackHandlerFactory
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/4/2022:19
 */
@Component
public class OssrsCallBackHandlerFactory implements ApplicationRunner {

    private ConcurrentHashMap<OssrsCallBackActionEnum,OssrsCallBackHandler> cacheMap = new ConcurrentHashMap<>(10);

    public OssrsCallBackHandler getOssrsCallBackHandler(OssrsCallBackActionEnum actionEnum){
        return cacheMap.get(actionEnum);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] beanNamesForType = SpringUtil.getBeanNamesForType(OssrsCallBackHandler.class);
        for (String beanName : beanNamesForType) {
            OssrsCallBackHandler ossrsCallBackHandler = SpringUtil.getBean(beanName, OssrsCallBackHandler.class);
            cacheMap.put(ossrsCallBackHandler.handlerAction(),ossrsCallBackHandler);
        }
    }
}
