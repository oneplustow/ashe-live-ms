package cn.oneplustow.api.sc.service.fallback;

import cn.oneplustow.api.sc.model.ApiInfoSaveReq;
import cn.oneplustow.api.sc.service.ApiInfoFeginApi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class ApiInfoFeginApiFallback implements FallbackFactory<ApiInfoFeginApi> {
    @Override
    public ApiInfoFeginApi create(Throwable throwable) {
        return new ApiInfoFeginApi() {
            @Override
            public Boolean saveApiInfo(List<ApiInfoSaveReq> apiInfoModelList) {
                return false;
            }
        };
    }
}
