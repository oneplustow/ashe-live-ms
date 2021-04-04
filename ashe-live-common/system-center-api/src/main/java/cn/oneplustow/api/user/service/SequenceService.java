package cn.oneplustow.api.user.service;

import cn.oneplustow.api.user.model.SimpleUser;
import cn.oneplustow.api.user.model.SysUserModel;
import cn.oneplustow.api.user.service.fallback.SequenceServiceFallback;
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
@FeignClient(value = "system-center",fallbackFactory = SequenceServiceFallback.class)
@RequestMapping("system-center/fegin/system/sequence/")
public interface SequenceService {

    /**
     * 获取序列号
     * @param seqName
     * @return
     */
    @GetMapping("apply")
    public String apply(@RequestParam("seqName") String seqName);
}
