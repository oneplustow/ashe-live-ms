package cn.oneplustow.sc.fegin;


import cn.oneplustow.api.sc.model.SimpleUser;
import cn.oneplustow.api.sc.model.UserResp;
import cn.oneplustow.api.sc.service.UserFeginApi;
import cn.oneplustow.api.sc.vo.SaveUserDto;
import cn.oneplustow.sc.entity.SysUser;
import cn.oneplustow.sc.service.ISysUserService;
import cn.opl.mapstruct.MapStructContext;
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
@Api(tags = "用户信息控制器")
@RestController
@RequestMapping("/fegin/system/user")
public class UserFeginApiImpl implements UserFeginApi {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private MapStructContext mapStructContext;


    /**
     * 根据用户编号获取详细信息
     */
    @Override
    @GetMapping("getUserByName")
    public UserResp getUserByName(@RequestParam("userName") String userName) {
        SysUser sysUser = userService.selectUserByUserName(userName);
        return mapStructContext.conver(sysUser, UserResp.class);
    }

    @Override
    @GetMapping("getSimpleUserListById")
    public List<SimpleUser> getSimpleUserListById(@RequestParam("userIdSet") Set<String> userIdSet) {
        return userService.selectSimpleUserListById(userIdSet);
    }

    @Override
    @PostMapping("saveMemberUser")
    public Long saveMemberUser(@RequestBody SaveUserDto saveUserDto) {
        return userService.saveMemberUser(saveUserDto);
    }
}