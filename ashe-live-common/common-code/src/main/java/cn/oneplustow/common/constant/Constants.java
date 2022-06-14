package cn.oneplustow.common.constant;

import io.jsonwebtoken.Claims;

import java.util.Arrays;
import java.util.List;

/**
 * 通用常量信息
 *
 */
public class Constants {

    public interface OssCloud{

        /**
         * OSS模块KEY
         */
        String SYS_OSS_KEY = "sys_oss:";

        /**
         * 云存储配置KEY
         */
        String CLOUD_STORAGE_CONFIG_KEY = "CloudStorageConfig";

        /**
         * 缓存配置KEY
         */
        String CACHE_CONFIG_KEY = SYS_OSS_KEY + CLOUD_STORAGE_CONFIG_KEY;

        /**
         * 预览列表资源开关Key
         */
        String PEREVIEW_LIST_RESOURCE_KEY = "sys.oss.previewListResource";

        /**
         * 系统数据ids
         */
        List<Integer> SYSTEM_DATA_IDS = Arrays.asList(1, 2, 3, 4);
    }

    public interface SysConfigKey {
        String 短信配置 = "sys_sms_config";
        String 微信消息模板 = "wechat.msg.template.test";
    }

    /**
     * 全局是否
     */
    public interface YseNo {
        String NO = "0";
        String YSE = "1";
    }

    /**
     * 站内信状态
     */
    public interface InteriorMessageStatus {
        String 未读 = "0";
        String 已读 = "1";
    }

    public interface DataScope {
        String 所有数据权限 = "1";
        String 自定义数据权限 = "2";
        String 本部门数据权限 = "3";
        String 本部门及以下数据权限 = "4";
        String 仅本人 = "5";
        String 本销控区 = "6";
    }


    public static String TRIGGER_OPTIMISTIC_LOCK = "TriggerOptimisticLock";

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户ID
     */
    public static final String JWT_USERID = "userid";

    /**
     * 用户名称
     */
    public static final String JWT_USERNAME = Claims.SUBJECT;

    /**
     * 用户头像
     */
    public static final String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    public static final String JWT_CREATED = "created";

    /**
     * 用户权限
     */
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

}
