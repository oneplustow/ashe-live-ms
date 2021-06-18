package cn.oneplustow.api.sc.service;

import cn.oneplustow.api.sc.model.SysApiInfoModel;
import cn.oneplustow.api.sc.service.fallback.ApiInfoServiceFallback;
import cn.oneplustow.api.sc.service.fallback.MenuServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * @author cc
 * @date 2020/11/3 11:07
 */
@FeignClient(value = "system-center",fallbackFactory = ApiInfoServiceFallback.class)
@RequestMapping("system-center/fegin/system/apiInfo")
public interface ApiInfoService {

    /**
     * 保存api接口信息
     * @param apiInfoModelList
     * @return
     */
    Boolean saveApiInfo(List<SysApiInfoModel> apiInfoModelList);
}
