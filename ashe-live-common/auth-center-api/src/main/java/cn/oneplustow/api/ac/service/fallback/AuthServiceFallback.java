package cn.oneplustow.api.ac.service.fallback;

import cn.oneplustow.api.ac.model.LoginUser;
import cn.oneplustow.api.ac.service.AuthFeginApi;
import cn.oneplustow.api.fallback.BaseFallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class AuthServiceFallback extends BaseFallbackFactory<AuthFeginApi> {
    @Override
    public AuthFeginApi doCreate(Throwable throwable) {
        return new AuthFeginApi(){
            @Override
            public LoginUser getLoginUserInfo(String authorization) {
                return null;
            }
        };
    }
}
