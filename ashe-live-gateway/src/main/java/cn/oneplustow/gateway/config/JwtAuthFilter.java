package cn.oneplustow.gateway.config;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.api.ac.model.LoginUser;
import cn.oneplustow.api.ac.service.AuthService;
import cn.oneplustow.api.sc.service.MenuService;
import cn.oneplustow.common.domain.AjaxResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author cc
 * @date 2020/11/5 13:29
 */
@Component
public class JwtAuthFilter implements GlobalFilter {

    @Autowired
    private AuthService authService;

    @Autowired
    private MenuService menuService;

    @Value("${fo.auth.excludeUrls}")
    private List<String> excludeUrls;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        if(!isIntercept(exchange)){return chain.filter(exchange);}
        String authorization = request.getHeaders().getFirst("authorization");
        LoginUser userInfo = authService.getUserInfo(authorization);
        if (!isPermission(userInfo,uri.getPath())) {
            String msg = StrUtil.format("请求访问：{}，认证失败，无法访问系统资源", uri.getPath());
            String msgJson = JSONObject.toJSONString(AjaxResult.error(HttpStatus.UNAUTHORIZED.value(), msg));
            return authErro(exchange.getResponse(),HttpStatus.OK, msgJson);
        }
        String loginUserString = JSONObject.toJSONString(userInfo);
        request = request.mutate()
                .header("login_user", Base64.encode(loginUserString)).build();
        return chain.filter(exchange.mutate().request(request).build());
    }

    private boolean isPermission(LoginUser userInfo,String uriPath) {
        if(userInfo == null || userInfo.getUser() == null){
            return false;
        }
        if (userInfo.getUser().isAdmin()) {return true;}
        return menuService.accessPermissionByUrl(userInfo.getUser().getUserId(),uriPath);
    }

    private boolean isIntercept(ServerWebExchange exchange){
        URI uri = exchange.getRequest().getURI();
        for (String ignore : excludeUrls) {
            if(antPathMatcher.match(ignore,uri.getPath())){return false;}
        }
        return true;
    }

    /**
     * 认证错误输出
     * @param resp 响应对象
     * @param mess 错误信息
     * @return
     */
    private Mono<Void> authErro(ServerHttpResponse resp,HttpStatus httpStatus, String mess) {
        resp.setStatusCode(httpStatus);
        resp.getHeaders().add("Content-Type","application/json;charset=UTF-8");
        DataBuffer buffer = resp.bufferFactory().wrap(mess.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Mono.just(buffer));
    }
}
