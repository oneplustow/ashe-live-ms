package cn.oneplustow.api.user.service.fallback;

import cn.oneplustow.api.user.model.SysUserModel;
import cn.oneplustow.api.user.service.RoleService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class RoleServiceFallback implements FallbackFactory<RoleService> {
    @Override
    public RoleService create(Throwable throwable) {
        return new RoleService(){
            @Override
            public List<String> selectRolePermissionByUserId(Long userid) {
                return new ArrayList<>();
            }
        };
    }
}
