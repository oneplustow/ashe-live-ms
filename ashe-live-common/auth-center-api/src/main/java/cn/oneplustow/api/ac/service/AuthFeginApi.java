package cn.oneplustow.api.ac.service;

import cn.oneplustow.api.ac.model.LoginUser;
import cn.oneplustow.api.ac.service.fallback.AuthServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author cc
 * @date 16/09/2020 11:03
 */
@FeignClient(value = "auth-center",fallbackFactory = AuthServiceFallback.class)
@RequestMapping("auth-center/fegin")
public interface AuthFeginApi {

    /**
     * 获取登录用户信息
     * @param authorization
     * @return
     */
    @GetMapping("getLoginUserInfo")
    LoginUser getLoginUserInfo(@RequestParam("authorization") String authorization);
}
