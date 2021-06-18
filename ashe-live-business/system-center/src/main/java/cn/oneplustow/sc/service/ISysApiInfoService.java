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
public interface ISysApiInfoService extends IService<SysApiInfo>
{

    boolean init();

    void saveApiInfo(List<SysApiInfo> apiInfoList);
}
