package cn.oneplustow.api.ac.model;


import cn.oneplustow.api.sc.model.SysUserModel;
import lombok.Data;

import java.util.Set;

/**
 * 登录用户身份权限
 * 
 * @author ruoyi
 */
@Data
public class LoginUser
{
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登陆时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Set<String> permissions;
    /**
     * 权限列表
     */
    private Set<String> urls;

    /**
     * 用户信息
     */
    private SysUserModel user;

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public LoginUser()
    {
    }

    public LoginUser(SysUserModel user, Set<String> permissions)
    {
        this.user = user;
        this.permissions = permissions;
    }

}
