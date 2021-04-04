package cn.oneplustow.api.auth.service.fallback;

import cn.oneplustow.api.auth.model.LoginUser;
import cn.oneplustow.api.auth.service.AuthService;
import cn.oneplustow.api.user.model.SysUserModel;
import cn.oneplustow.api.user.service.UserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class AuthServiceFallback implements FallbackFactory<AuthService> {
    @Override
    public AuthService create(Throwable throwable) {
        return new AuthService(){
            @Override
            public LoginUser getUserInfo(String authorization) {
                return null;
            }
        };
    }
}
