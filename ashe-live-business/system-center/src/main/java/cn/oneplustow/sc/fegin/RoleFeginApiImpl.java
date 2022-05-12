package cn.oneplustow.sc.fegin;


import cn.hutool.core.util.ObjectUtil;
import cn.oneplustow.api.sc.service.RoleFeginApi;
import cn.oneplustow.sc.service.ISysRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Api(tags = "用户信息控制器")
@RestController
@RequestMapping("/fegin/system/role")
public class RoleFeginApiImpl implements RoleFeginApi {
    @Autowired
    private ISysRoleService roleService;

    /**
     * 根据用户编号获取详细信息
     */
    @Override
    @GetMapping("getRolePermissionByUserId")
    public Set<String> getRolePermissionByUserId(@RequestParam("id") Long id) {
        Set<String> roleSet = roleService.selectRolePermissionByUserId(id);
        return roleSet;
    }

}