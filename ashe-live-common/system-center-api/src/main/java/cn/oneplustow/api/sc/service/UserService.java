package cn.oneplustow.api.sc.service;

import cn.oneplustow.api.sc.model.SimpleUser;
import cn.oneplustow.api.sc.model.SysUserModel;
import cn.oneplustow.api.sc.service.fallback.UserServiceFallback;
import cn.oneplustow.api.sc.vo.SaveUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 保存会员用户
     * @param saveUserDto
     * @return
     */
    @PostMapping("saveMemberUser")
    public Long saveMemberUser(@RequestBody SaveUserDto saveUserDto);
}
