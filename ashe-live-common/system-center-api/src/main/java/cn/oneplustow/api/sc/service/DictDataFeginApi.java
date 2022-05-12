package cn.oneplustow.api.sc.service;

import cn.oneplustow.api.sc.model.DictDataResp;
import cn.oneplustow.api.sc.service.fallback.DictDataFeginApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author cc
 * @date 16/09/2020 11:03
 */
@FeignClient(value = "system-center",fallbackFactory = DictDataFeginApiFallback.class)
@RequestMapping("system-center/fegin/system/dictData/")
public interface DictDataFeginApi {
    /**
     *  通过dict类型获取字典值
     * @param dictType
     * @return
     */
    @GetMapping("selectDictDataByType")
    List<DictDataResp> selectDictDataByType(@RequestParam("dictType") String dictType);

}
