package cn.oneplustow.api.user.service;

import cn.oneplustow.api.user.model.SimpleUser;
import cn.oneplustow.api.user.model.SysUserModel;
import cn.oneplustow.api.user.service.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * @author cc
 * @date 16/09/2020 11:03
 */
@FeignClient(value = "system-center",fallbackFactory = UserServiceFallback.class)
@RequestMapping("system-center/fegin/system/user")
public interface UserService {

    /**
     * 获取用户信息
     * @param userName
     * @return
     */
    @GetMapping("getUserByName")
    SysUserModel getUserByName(@RequestParam("userName") String userName);

    /**
     * 获取用户信息
     * @param userIdSet
     * @return
     */
    @GetMapping("getSimpleUserListById")
    List<SimpleUser> getSimpleUserListById(@RequestParam("userIdSet") Set<String> userIdSet);
}
