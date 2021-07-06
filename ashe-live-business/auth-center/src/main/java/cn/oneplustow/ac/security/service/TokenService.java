package cn.oneplustow.ac.security.service;


import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.oneplustow.ac.entity.LoginUserDetails;
import cn.oneplustow.common.constant.Constants;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.common.web.util.AddressUtils;
import cn.oneplustow.common.web.util.IpUtils;
import cn.oneplustow.common.web.util.ServletUtils;
import cn.oneplustow.config.redis.util.RedisCache;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 * 
 * @author ruoyi
 */
@Component
public class TokenService
{
    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    private RedisCache redisCache;

    /**
     * 获取用户身份信息
     * 
     * @return 用户信息
     */
    public LoginUserDetails getLoginUser(HttpServletRequest request)
    {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StrUtil.isEmpty(token)){
            throw new WarningMessageException("无法获取用户信息");
        }
        Claims claims = parseToken(token);
        // 解析对应的权限以及用户信息
        String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
        String userKey = getTokenKey(uuid);
        LoginUserDetails user = redisCache.getCacheObject(userKey);
        return user;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(LoginUserDetails loginUserDetails)
    {
        if (ObjectUtil.isNotNull(loginUserDetails) && StrUtil.isNotEmpty(loginUserDetails.getToken()))
        {
            String userKey = getTokenKey(loginUserDetails.getToken());
            redisCache.setCacheObject(userKey, loginUserDetails);
        }
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUser(String token)
    {
        if (StrUtil.isNotEmpty(token))
        {
            String userKey = getTokenKey(token);
            redisCache.deleteObject(userKey);
        }
    }

    /**
     * 创建令牌
     * 
     * @param loginUserDetails 用户信息
     * @return 令牌
     */
    public String createToken(LoginUserDetails loginUserDetails)
    {
        String token = IdUtil.fastUUID();
        loginUserDetails.setToken(token);
        setUserAgent(loginUserDetails);
        refreshToken(loginUserDetails);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     * 
     * @param token 令牌
     * @return 令牌
     */
    public void verifyToken(LoginUserDetails loginUserDetails)
    {
        long expireTime = loginUserDetails.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN)
        {
            refreshToken(loginUserDetails);
        }
    }

    /**
     * 刷新令牌有效期
     * 
     * @param loginUserDetails 登录信息
     */
    public void refreshToken(LoginUserDetails loginUserDetails)
    {
        loginUserDetails.setLoginTime(System.currentTimeMillis());
        loginUserDetails.setExpireTime(loginUserDetails.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUserDetails.getToken());
        redisCache.setCacheObject(userKey, loginUserDetails, expireTime, TimeUnit.MINUTES);
    }
    
    /**
     * 设置用户代理信息
     * 
     * @param loginUserDetails 登录信息
     */
    public void setUserAgent(LoginUserDetails loginUserDetails)
    {
        UserAgent userAgent = UserAgentUtil.parse(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        loginUserDetails.setIpaddr(ip);
        loginUserDetails.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        loginUserDetails.setBrowser(userAgent.getBrowser().getName());
        loginUserDetails.setOs(userAgent.getOs().getName());
    }
    
    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims)
    {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token)
    {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request)
    {
        String token = request.getHeader(header);
        if(StrUtil.isBlank(token)){
            token = request.getParameter(header);
        }
        if (StrUtil.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX))
        {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getTokenKey(String uuid)
    {
        return Constants.LOGIN_TOKEN_KEY + uuid;
    }
}
