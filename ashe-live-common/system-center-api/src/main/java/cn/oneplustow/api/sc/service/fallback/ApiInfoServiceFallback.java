package cn.oneplustow.api.sc.service.fallback;

import cn.oneplustow.api.sc.model.SysApiInfoModel;
import cn.oneplustow.api.sc.service.ApiInfoService;
import cn.oneplustow.api.sc.service.MenuService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class ApiInfoServiceFallback implements FallbackFactory<ApiInfoService> {
    @Override
    public ApiInfoService create(Throwable throwable) {
        return new ApiInfoService() {
            @Override
            public Boolean saveApiInfo(List<SysApiInfoModel> apiInfoModelList) {
                return false;
            }
        };
    }
}
