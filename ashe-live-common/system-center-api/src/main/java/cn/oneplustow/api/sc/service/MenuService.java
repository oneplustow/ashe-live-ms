package cn.oneplustow.api.sc.service;

import cn.oneplustow.api.sc.service.fallback.MenuServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * @author cc
 * @date 2020/11/3 11:07
 */
@FeignClient(value = "system-center",fallbackFactory = MenuServiceFallback.class)
@RequestMapping("system-center/fegin/system/menu")
public interface MenuService {
    /**
     * 获取用户角色权限
     * @param id
     * @return
     */
    @GetMapping("getMenuPermsByUserId")
    Set<String> selectMenuPermsByUserId(@RequestParam("id") Long id);

    /**
     * 获取指定用户是否有访问指定url的权限
     * @param userId
     * @param url
     * @return
     */
    @GetMapping("accessPermissionByUrl")
    Boolean accessPermissionByUrl(@RequestParam("userId") Long userId,@RequestParam("url") String url);
}
