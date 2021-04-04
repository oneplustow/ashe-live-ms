package cn.oneplustow.api.auth.service;

import cn.oneplustow.api.auth.model.LoginUser;
import cn.oneplustow.api.auth.service.fallback.AuthServiceFallback;
import cn.oneplustow.api.user.model.SysUserModel;
import cn.oneplustow.api.user.service.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author cc
 * @date 16/09/2020 11:03
 */
@FeignClient(value = "auth-center",fallbackFactory = AuthServiceFallback.class)
@RequestMapping("auth-center")
public interface AuthService {

    /**
     * 获取登录用户信息
     * @param authorization
     * @return
     */
    @GetMapping("getUserInfo")
    LoginUser getUserInfo(@RequestParam("authorization") String authorization);
}
