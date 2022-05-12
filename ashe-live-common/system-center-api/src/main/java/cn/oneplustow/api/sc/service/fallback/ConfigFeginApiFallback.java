package cn.oneplustow.api.sc.service.fallback;

import cn.oneplustow.api.sc.service.ConfigFeginApi;
import cn.oneplustow.api.sc.service.RoleFeginApi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class ConfigFeginApiFallback implements FallbackFactory<ConfigFeginApi> {
    @Override
    public ConfigFeginApi create(Throwable throwable) {
        return new ConfigFeginApi(){
            @Override
            public String getConfigByKey(String key) {
                return null;
            }
        };
    }
}
