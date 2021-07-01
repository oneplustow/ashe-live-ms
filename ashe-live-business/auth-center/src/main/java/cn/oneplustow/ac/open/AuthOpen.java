package cn.oneplustow.ac.open;

import cn.oneplustow.ac.entity.LoginUserDetails;
import cn.oneplustow.ac.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cc
 * @date 2020/11/5 13:53
 */
@RestController
public class AuthOpen {
    @Autowired
    private TokenService tokenService;

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getUserInfo")
    public LoginUserDetails getInfo(HttpServletRequest request)
    {
        return tokenService.getLoginUser(request);
    }
}
