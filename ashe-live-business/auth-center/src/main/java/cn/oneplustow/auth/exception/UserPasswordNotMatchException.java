package cn.oneplustow.auth.exception;


import cn.oneplustow.common.exception.BaseException;

/**
 * 用户密码不正确或不符合规范异常类
 * 
 * @author ruoyi
 */
public class UserPasswordNotMatchException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user", "user.password.not.match", null, null);
    }
}
