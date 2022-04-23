package cn.oneplustow.api.sc.service.fallback;

import cn.oneplustow.api.sc.service.MenuFeginApi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class MenuFeginApiFallback implements FallbackFactory<MenuFeginApi> {
    @Override
    public MenuFeginApi create(Throwable throwable) {
        return new MenuFeginApi() {
            @Override
            public Set<String> getMenuPermsByUserId(Long userid) {
                return new HashSet<>();
            }

            @Override
            public Boolean accessPermissionByUrl(Long userId, String url) {
                return false;
            }
        };
    }
}
