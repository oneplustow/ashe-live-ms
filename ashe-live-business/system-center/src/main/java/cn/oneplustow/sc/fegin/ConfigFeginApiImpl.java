package cn.oneplustow.sc.fegin;


import cn.oneplustow.api.sc.service.ConfigFeginApi;
import cn.oneplustow.api.sc.service.RoleFeginApi;
import cn.oneplustow.sc.service.ISysConfigService;
import cn.oneplustow.sc.service.ISysRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Api(tags = "用户信息控制器")
@RestController
@RequestMapping("/fegin/system/config")
public class ConfigFeginApiImpl implements ConfigFeginApi {
    @Autowired
    private ISysConfigService configService;

    /**
     * 根据用户编号获取详细信息
     */
    @Override
    @GetMapping("getConfigByKey")
    public String getConfigByKey(@RequestParam String key) {
        return configService.selectConfigByKey(key);
    }

}