package cn.oneplustow.ac.open;

import cn.oneplustow.ac.entity.LoginUserDetails;
import cn.oneplustow.ac.security.service.TokenService;
import cn.oneplustow.api.ac.model.LoginUser;
import cn.oneplustow.api.ac.service.AuthFeginApi;
import cn.opl.mapstruct.MapStructContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cc
 * @date 2020/11/5 13:53
 */
@RestController
public class AuthFeginApiImpl implements AuthFeginApi {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MapStructContext mapStructContext;

    /**
     * 获取登录用户信息
     * @return 用户信息
     */
    @Override
    @GetMapping("getLoginUserInfo")
    public LoginUser getLoginUserInfo(String authorization) {
        ServletRequestAttributes servletcontext = ServletRequestAttributes.class.
            cast(RequestContextHolder.getRequestAttributes());
        LoginUserDetails loginUser = tokenService.getLoginUser(servletcontext.getRequest());
        return mapStructContext.conver(loginUser, LoginUser.class);
    }
}
