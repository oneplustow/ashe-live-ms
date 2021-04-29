package cn.oneplustow.ac.security.handle;


import cn.hutool.core.util.ObjectUtil;
import cn.oneplustow.ac.entity.LoginUserDetails;
import cn.oneplustow.ac.factory.AsyncFactory;
import cn.oneplustow.ac.security.service.TokenService;
import cn.oneplustow.common.constant.Constants;
import cn.oneplustow.common.web.constant.HttpStatus;
import cn.oneplustow.common.manager.AsyncManager;
import cn.oneplustow.common.web.util.ServletUtils;
import cn.oneplustow.common.web.domain.AjaxResult;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 * 
 * @author ruoyi
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     * 
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        LoginUserDetails loginUser = tokenService.getLoginUser(request);
        if (ObjectUtil.isNotNull(loginUser))
        {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
    }
}
