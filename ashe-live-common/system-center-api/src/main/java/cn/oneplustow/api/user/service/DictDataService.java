package cn.oneplustow.api.user.service;

import cn.oneplustow.api.user.model.SimpleUser;
import cn.oneplustow.api.user.model.SysDictDataModel;
import cn.oneplustow.api.user.model.SysUserModel;
import cn.oneplustow.api.user.service.fallback.DictDataServiceFallback;
import cn.oneplustow.api.user.service.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * @author cc
 * @date 16/09/2020 11:03
 */
@FeignClient(value = "system-center",fallbackFactory = DictDataServiceFallback.class)
@RequestMapping("system-center/fegin/system/dictData/")
public interface DictDataService {
    /**
     *  通过dict类型获取字典值
     * @param dictType
     * @return
     */
    @GetMapping("selectDictDataByType")
    List<SysDictDataModel> selectDictDataByType(@RequestParam("dictType") String dictType);

}
