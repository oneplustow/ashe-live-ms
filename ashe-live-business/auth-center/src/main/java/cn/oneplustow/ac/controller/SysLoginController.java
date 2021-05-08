package cn.oneplustow.ac.controller;


import cn.oneplustow.ac.entity.LoginUserDetails;
import cn.oneplustow.ac.security.service.TokenService;
import cn.oneplustow.ac.util.SecurityUtils;
import cn.oneplustow.api.sc.model.SysUserModel;
import cn.oneplustow.api.sc.service.MenuService;
import cn.oneplustow.api.sc.service.UserService;
import cn.oneplustow.common.constant.Constants;
import cn.oneplustow.common.domain.AjaxResult;
import cn.oneplustow.ac.security.service.SysLoginService;
import cn.oneplustow.ac.security.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@Api(tags = "登录控制器")
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    /**
     * 登录方法
     *
     * @param username 用户名
     * @param password 密码
     * @param captcha 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    @ApiOperation("登录接口")
    @PostMapping("/login")
    public AjaxResult login(String username, String password, String code, String uuid,String clientType)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(username, password, code, uuid, clientType);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo(String userName)
    {
        LoginUserDetails loginUser = SecurityUtils.getLoginUser();
        SysUserModel user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

}
