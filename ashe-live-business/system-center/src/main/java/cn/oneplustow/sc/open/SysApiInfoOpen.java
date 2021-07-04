package cn.oneplustow.sc.open;

import cn.oneplustow.sc.entity.SysApiInfo;
import cn.oneplustow.sc.service.ISysApiInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author cc
 */
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
