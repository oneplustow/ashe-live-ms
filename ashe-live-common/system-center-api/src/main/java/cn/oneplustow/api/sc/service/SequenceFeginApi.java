package cn.oneplustow.api.sc.service;

import cn.oneplustow.api.sc.service.fallback.SequenceFeginApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author cc
 * @date 16/09/2020 11:03
 */
@FeignClient(value = "system-center",fallbackFactory = SequenceFeginApiFallback.class)
@RequestMapping("system-center/fegin/system/sequence/")
public interface SequenceFeginApi {

    /**
     * 获取序列号
     * @param seqName
     * @return
     */
    @GetMapping("apply")
    public String apply(@RequestParam("seqName") String seqName);
}
