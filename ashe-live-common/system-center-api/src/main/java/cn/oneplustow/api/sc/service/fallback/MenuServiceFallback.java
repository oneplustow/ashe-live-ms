package cn.oneplustow.api.sc.service.fallback;

import cn.oneplustow.api.sc.service.MenuService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class MenuServiceFallback implements FallbackFactory<MenuService> {
    @Override
    public MenuService create(Throwable throwable) {
        return new MenuService() {
            @Override
            public Set<String> selectMenuPermsByUserId(Long userid) {
                return new HashSet<>();
            }

            @Override
            public Boolean accessPermissionByUrl(Long userId, String url) {
                return false;
            }
        };
    }
}
