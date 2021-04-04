package cn.oneplustow.api.auth.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.api.auth.model.LoginUser;
import cn.oneplustow.common.exception.CustomException;
import cn.oneplustow.common.web.constant.HttpStatus;
import cn.oneplustow.common.web.util.ServletUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author cc
 * @date 2020/11/5 14:36
 */
public class SecurityUtils {
    public static String getUsername() {
        return getLoginUser().getUser().getUserName();
    }

    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    public static LoginUser getLoginUser() {
        String loginUserString = ServletUtils.getRequest().getHeader("login_user");
        if(StrUtil.isBlank(loginUserString)) {
            throw new CustomException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
        loginUserString = Base64.decodeStr(loginUserString);
        LoginUser loginUser = JSONObject.parseObject(loginUserString, LoginUser.class);
        return loginUser;
    }
}
