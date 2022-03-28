package cn.oneplustow.sc.fegin;

import cn.oneplustow.api.sc.model.ApiInfoSaveReq;
import cn.oneplustow.api.sc.service.ApiInfoFeginApi;
import cn.oneplustow.sc.entity.SysApiInfo;
import cn.oneplustow.sc.service.ISysApiInfoService;
import cn.opl.mapstruct.MapStructContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author cc
 */
@RestController
@RequestMapping("/fegin/system/apiInfo")
public class ApiInfoFeginApiImpl implements ApiInfoFeginApi {
    @Autowired
    private ISysApiInfoService apiInfoService;
    @Autowired
    private MapStructContext mapStructContext;


    @Override
    public Boolean saveApiInfo(@RequestBody List<ApiInfoSaveReq> apiInfoModelList) {
        return apiInfoService.saveApiInfo(mapStructContext.conver(apiInfoModelList, SysApiInfo.class));
    }
}
