package cn.oneplustow.api.ac.service.fallback;

import cn.oneplustow.api.ac.model.LoginUser;
import cn.oneplustow.api.ac.service.AuthService;
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
