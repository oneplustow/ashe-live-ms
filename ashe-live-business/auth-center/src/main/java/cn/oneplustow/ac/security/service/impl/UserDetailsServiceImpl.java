package cn.oneplustow.ac.security.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.ac.security.service.SysPermissionService;
import cn.oneplustow.api.sc.model.SysUserModel;
import cn.oneplustow.api.sc.service.UserService;
import cn.oneplustow.ac.entity.LoginUserDetails;
import cn.oneplustow.api.uc.service.MemberService;
import cn.oneplustow.api.uc.service.model.MemberModel;
import cn.oneplustow.common.enume.UserStatus;
import cn.oneplustow.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;
    @Autowired
    private MemberService memberService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        String[] clientTypeAndUserName = StrUtil.split(username, ":");
        String clientType = clientTypeAndUserName.length < 2 ? "sc":clientTypeAndUserName[0];
        if(StrUtil.equals(clientType,"sc")){
            SysUserModel user = userService.getUserByName(username);
        }
        if(StrUtil.equals(clientType,"uc")){
            log.info("用户中心登录 直接返回");
            MemberModel member = memberService.getMemberByName(username);
        }


        SysUserModel user = userService.getUserByName(username);
        if (ObjectUtil.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUserModel user)
    {
        return new LoginUserDetails(user, permissionService.getMenuPermission(user));
    }
}
