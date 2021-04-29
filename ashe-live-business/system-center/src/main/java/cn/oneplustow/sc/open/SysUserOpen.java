package cn.oneplustow.sc.open;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.oneplustow.api.sc.model.SimpleUser;
import cn.oneplustow.sc.entity.SysUser;
import cn.oneplustow.sc.service.ISysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Api(description = "用户信息控制器")
@RestController
@RequestMapping("/fegin/system/user")
public class SysUserOpen
{
    @Autowired
    private ISysUserService userService;


    /**
     * 根据用户编号获取详细信息
     */
    @GetMapping("getUserByName")
    public SysUser getUserByName(@RequestParam("userName") String userName)
    {
        if (ObjectUtil.isNotNull(userName))
        {
            SysUser sysUser = userService.selectUserByUserName(userName);
            return sysUser;
        }
        return null;
    }

    @GetMapping("getSimpleUserListById")
    public List<SimpleUser> getSimpleUserListById(@RequestParam("userIdSet") Set<String> userIdSet) {
        if (CollUtil.isNotEmpty(userIdSet))
        {
            return userService.selectSimpleUserListById(userIdSet);
        }
        return null;
    }
}