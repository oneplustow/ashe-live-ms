package cn.oneplustow.api.sc.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * @author cc
 */
@Data
public class SaveUserDto implements Serializable {

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

    /** 用户头像 */
    private String avatar;

    /** 密码 */
    private String password;

}
