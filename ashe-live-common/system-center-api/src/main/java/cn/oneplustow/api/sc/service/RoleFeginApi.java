package cn.oneplustow.api.sc.service;

import cn.oneplustow.api.sc.service.fallback.RoleFeginApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * @author cc
 * @date 2020/11/3 11:07
 */
@FeignClient(value = "system-center",fallbackFactory = RoleFeginApiFallback.class)
@RequestMapping("system-center/fegin/system/role/")
public interface RoleFeginApi {

    /**
     * 获取用户角色权限
     * @param id
     * @return
     */
    @GetMapping("getRolePermissionByUserId")
    Set<String> getRolePermissionByUserId(@RequestParam("id") Long id);
}
