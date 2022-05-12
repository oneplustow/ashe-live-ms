package cn.oneplustow.ac.controller;


import cn.oneplustow.ac.dto.LoginBody;
import cn.oneplustow.ac.security.service.SysLoginService;
import cn.oneplustow.ac.security.service.SysPermissionService;
import cn.oneplustow.api.ac.model.LoginUser;
import cn.oneplustow.api.ac.util.SecurityUtils;
import cn.oneplustow.api.sc.model.UserResp;
import cn.oneplustow.common.constant.Constants;
import cn.oneplustow.common.domain.AjaxResult;
import cn.oneplustow.common.web.controller.GlobalExceptionHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@Api(tags = "登录控制器")
@RestController
public class SysLoginController extends GlobalExceptionHandler
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private SysPermissionService permissionService;

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
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token =loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
            loginBody.getUuid());
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
        LoginUser loginUser = SecurityUtils.getLoginUser();
        UserResp user = loginUser.getUser();
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
