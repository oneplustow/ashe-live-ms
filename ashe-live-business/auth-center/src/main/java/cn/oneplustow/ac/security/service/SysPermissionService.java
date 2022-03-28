package cn.oneplustow.ac.security.service;


import cn.oneplustow.api.sc.model.UserResp;
import cn.oneplustow.api.sc.service.MenuFeginApi;
import cn.oneplustow.api.sc.service.RoleFeginApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户权限处理
 *
 * @author ruoyi
 */
@Component
public class SysPermissionService {
    @Autowired
    private RoleFeginApi roleFeginApi;

    @Autowired
    private MenuFeginApi menuFeginApi;

    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(UserResp user) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            roles.add("admin");
        } else {
            roles.addAll(roleFeginApi.getRolePermissionByUserId(user.getUserId()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(UserResp user) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            roles.add("*:*:*");
        } else {
            roles.addAll(menuFeginApi.getMenuPermsByUserId(user.getUserId()));
        }
        return roles;
    }
}
