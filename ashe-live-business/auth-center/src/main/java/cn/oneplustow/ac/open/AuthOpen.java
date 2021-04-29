package cn.oneplustow.ac.open;

import cn.oneplustow.ac.entity.LoginUserDetails;
import cn.oneplustow.ac.util.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cc
 * @date 2020/11/5 13:53
 */
@RestController
public class AuthOpen {

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getUserInfo")
    public LoginUserDetails getInfo()
    {

        LoginUserDetails loginUser = SecurityUtils.getLoginUser();
        return loginUser;
    }
}
