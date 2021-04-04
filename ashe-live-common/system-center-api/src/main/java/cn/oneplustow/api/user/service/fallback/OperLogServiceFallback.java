package cn.oneplustow.api.user.service.fallback;

import cn.oneplustow.api.user.model.SysOperLogModel;
import cn.oneplustow.api.user.service.OperLogService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class OperLogServiceFallback implements FallbackFactory<OperLogService> {
    @Override
    public OperLogService create(Throwable throwable) {
        return new OperLogService(){
            @Override
            public Boolean insertOperlog(SysOperLogModel sysOperLog) {
                return false;
            }
        };
    }
}
