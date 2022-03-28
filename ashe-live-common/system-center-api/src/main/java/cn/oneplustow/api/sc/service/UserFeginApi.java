package cn.oneplustow.api.sc.service;

import cn.oneplustow.api.sc.model.SimpleUser;
import cn.oneplustow.api.sc.model.UserResp;
import cn.oneplustow.api.sc.service.fallback.UserFeginApiFallback;
import cn.oneplustow.api.sc.vo.SaveUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author cc
 * @date 16/09/2020 11:03
 */
@FeignClient(value = "system-center",fallbackFactory = UserFeginApiFallback.class)
@RequestMapping("system-center/fegin/system/user")
public interface UserFeginApi {

    /**
     * 获取用户信息
     * @param userName
     * @return
     */
    @GetMapping("getUserByName")
    UserResp getUserByName(@RequestParam("userName") String userName);

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
    Long saveMemberUser(@RequestBody SaveUserDto saveUserDto);
}
