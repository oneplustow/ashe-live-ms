package cn.oneplustow.sc.open;

import cn.hutool.core.util.ObjectUtil;
import cn.oneplustow.sc.entity.SysApiInfo;
import cn.oneplustow.sc.entity.SysDictData;
import cn.oneplustow.sc.service.ISysApiInfoService;
import cn.oneplustow.sc.service.ISysDictDataService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fegin/system/apiInfo")
public class SysApiInfoOpen {
    @Autowired
    private ISysApiInfoService apiInfoService;

    /**
     * 根据用户编号获取详细信息
     */
    @GetMapping("saveApiInfo")
    public Boolean saveApiInfo(@RequestBody List<SysApiInfo> apiInfoList)
    {
        return apiInfoService.saveApiInfo(apiInfoList);
    }
}
