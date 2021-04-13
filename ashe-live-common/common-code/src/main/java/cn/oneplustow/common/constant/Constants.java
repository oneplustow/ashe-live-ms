package cn.oneplustow.common.constant;

import io.jsonwebtoken.Claims;

/**
 * 通用常量信息
 * 
 * @author ruoyi
 */
public class Constants
{
    public interface SteamServerStatus{
        String WAIT_CONNECTION = "0";
        String CONNECTION_SUCCESS = "1";
        String CONNECTION_FAILURE = "2";
    }
    public interface DataScope{
        String 所有数据权限 = "1";
        String 自定义数据权限 = "2";
        String 本部门数据权限 = "3";
        String 本部门及以下数据权限 = "4";
        String 仅本人 = "5";
        String 本销控区 = "6";
    }
    public interface OrderStatus{
        /**订单取消*/
        String CANCEL_ORDER = "0";
        /**等待确认*/
        String PLACE_ORDER = "1";
        /**等待配货*/
        String WAIT_ASSORTING = "2";
        /**配货完成待发货*/
        String WAIT_DISPATCH = "3";
        /**已发货*/
        String ALREADY_DISPATCH = "4";
        /**已送达*/
        String ALREADY_DELIVERY = "5";
        /**订单已完成*/
        String ALREADY_ORDER = "6";
    }
    public interface AllocationStatus{
        /**待分配配货员*/
        String WAIT_ALLOCATION = "1";
        /**已分配配货员*/
        String ALREADY_ALLOCATION = "2";
        /**已完成配货*/
        String ALREADY_ASSORTING = "3";
    }
    public interface DispatchBillStatus{
        /**待分配发货员*/
        String WAIT_DELIVERY_STAFF = "1";
        /**已分配发货员*/
        String ALREADY_DELIVERY_STAFF = "2";
        /**已送达*/
        String ALREADY_DELIVERY = "3";
        /**已确认*/
        String ALREADY_AFFIRM = "4";
    }
    public interface ClearingStatus{
        /**待结算*/
        String WAIT_CLEARING = "1";
        /**已结算*/
        String ALREADY_CLEARING = "2";
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
