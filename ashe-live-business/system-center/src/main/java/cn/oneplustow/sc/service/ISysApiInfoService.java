package cn.oneplustow.sc.service;

import cn.oneplustow.common.domain.AjaxResult;
import cn.oneplustow.sc.entity.SysApiInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 系统api信息Service接口
 * 
 * @author cc
 * @date 2021-06-18
 */
public interface ISysApiInfoService {
    /**
     * 批量保存api信息
     * @param apiInfoList
     * @return
     */
    boolean saveApiInfo(List<SysApiInfo> apiInfoList);
}
