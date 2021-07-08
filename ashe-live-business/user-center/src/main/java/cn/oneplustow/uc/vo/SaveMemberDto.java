package cn.oneplustow.uc.vo;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author cc
 */
@Data
public class SaveMemberDto implements Serializable {

    /** 用户账号 */
    @NotBlank(message = "用户名不能为空")
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
    @NotBlank(message = "密码不能为空")
    private String password;

}
