package cn.oneplustow.api.sc.service;

import cn.oneplustow.api.sc.service.fallback.RoleServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author cc
 * @date 2020/11/3 11:07
 */
@FeignClient(value = "system-center",fallbackFactory = RoleServiceFallback.class)
@RequestMapping("system-center/fegin/system/role/")
public interface RoleService {

    /**
     * 获取用户角色权限
     * @param id
     * @return
     */
    @GetMapping("getRolePermissionByUserId")
    List<String> selectRolePermissionByUserId(@RequestParam("id") Long id);
}
