package cn.oneplustow.api.sc.service.fallback;

import cn.oneplustow.api.sc.service.RoleFeginApi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class RoleFeginApiFallback implements FallbackFactory<RoleFeginApi> {
    @Override
    public RoleFeginApi create(Throwable throwable) {
        return new RoleFeginApi(){
            @Override
            public Set<String> getRolePermissionByUserId(Long userid) {
                return new HashSet<>();
            }
        };
    }
}
