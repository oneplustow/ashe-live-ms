package cn.oneplustow.user.open;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.web.controller.BaseController;
import cn.oneplustow.user.service.ISysMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Api(tags = "用户信息控制器")
@RestController
@RequestMapping("/fegin/system/menu")
public class SysMenuOpen extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    /**
     * 根据用户编号获取详细信息
     */
    @GetMapping("getMenuPermsByUserId")
    public Set<String> getMenuPermsByUserId(@RequestParam("id") Long id)
    {

        if (ObjectUtil.isNotNull(id))
        {
            Set<String> menuPermsSet = menuService.selectMenuPermsByUserId(id);
            return menuPermsSet;
        }
        return null;
    }

    /**
     * 获取指定用户是否有访问指定url的权限
     */
    @GetMapping("accessPermissionByUrl")
    public Boolean accessPermissionByUrl(@RequestParam("userId") Long userId,@RequestParam("url") String url)
    {
        if (ObjectUtil.isNull(userId) || StrUtil.isBlank(url)){
            return false;
        }
        return menuService.accessPermissionByUrl(userId,url);

    }


}