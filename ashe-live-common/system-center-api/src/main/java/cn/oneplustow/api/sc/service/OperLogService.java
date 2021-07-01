package cn.oneplustow.api.sc.service;

import cn.oneplustow.api.sc.model.SysOperLogModel;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author cc
 * @date 16/09/2020 11:03
 */
//@FeignClient(value = "system-center",fallbackFactory = OperLogServiceFallback.class)
//@RequestMapping("api/freshOrder/user")
public interface OperLogService {
    /**
     * 插入日志数据
     * @param sysOperLog
     * @return
     */
    @PostMapping("insertOperlog")
    Boolean insertOperlog(SysOperLogModel sysOperLog);
}
