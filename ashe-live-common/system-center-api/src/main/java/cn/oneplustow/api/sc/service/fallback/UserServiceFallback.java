package cn.oneplustow.api.sc.service.fallback;

import cn.oneplustow.api.sc.model.SimpleUser;
import cn.oneplustow.api.sc.model.SysUserModel;
import cn.oneplustow.api.sc.service.UserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class UserServiceFallback implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService(){
            @Override
            public SysUserModel getUserByName(String username) {
                return new SysUserModel();
            }

            @Override
            public List<SimpleUser> getSimpleUserListById(Set<String> userIdSet) {
                return new ArrayList<>();
            }
        };
    }
}
