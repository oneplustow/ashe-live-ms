package cn.oneplustow.api.sc.model;

import cn.hutool.core.bean.BeanUtil;
import lombok.Data;

/**
 * @author cc
 */
@Data
public class SimpleUser {
    /** 用户ID */
    private Long userId;

    /** 用户账号 */
    private String userName;

    /** 用户昵称 */
    private String nickName;

    /** 用户邮箱 */
    private String email;

    /** 手机号码 */
    private String phonenumber;

    /** 用户性别 */
    private String sex;
    /** 微信id */
    private String weChatId;

    public SimpleUser() {
    }
    public SimpleUser(SysUserModel sysUser) {
        BeanUtil.copyProperties(sysUser,this);
    }
}
