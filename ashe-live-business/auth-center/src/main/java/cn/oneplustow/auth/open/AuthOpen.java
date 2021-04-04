package cn.oneplustow.auth.open;

import cn.hutool.core.thread.ThreadUtil;
import cn.oneplustow.api.auth.model.LoginUser;
import cn.oneplustow.api.user.model.SysUserModel;
import cn.oneplustow.api.user.service.MenuService;
import cn.oneplustow.auth.entity.LoginUserDetails;
import cn.oneplustow.auth.security.service.SysPermissionService;
import cn.oneplustow.auth.util.SecurityUtils;
import cn.oneplustow.common.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

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
