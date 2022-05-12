package cn.oneplustow.api.sc.service;

import cn.oneplustow.api.sc.model.ApiInfoSaveReq;
import cn.oneplustow.api.sc.service.fallback.ApiInfoFeginApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author cc
 * @date 2020/11/3 11:07
 */
@FeignClient(value = "system-center",fallbackFactory = ApiInfoFeginApiFallback.class)
@RequestMapping("system-center/fegin/system/apiInfo")
public interface ApiInfoFeginApi {

    /**
     * 保存api接口信息
     * @param apiInfoModelList
     * @return
     */
    @GetMapping("saveApiInfo")
    Boolean saveApiInfo(List<ApiInfoSaveReq> apiInfoModelList);
}
