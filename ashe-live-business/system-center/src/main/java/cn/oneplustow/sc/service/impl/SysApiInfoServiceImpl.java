package cn.oneplustow.sc.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.sc.entity.SysApiInfo;
import cn.oneplustow.sc.mapper.SysApiInfoMapper;
import cn.oneplustow.sc.service.ISysApiInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 系统api信息Service业务层处理
 *
 * @author cc
 * @date 2021-06-18
 */
@Service
public class SysApiInfoServiceImpl extends ServiceImpl<SysApiInfoMapper, SysApiInfo> implements ISysApiInfoService {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${spring.application.name}")
    private String systemFlag;

    @Override
    public boolean saveApiInfo(List<SysApiInfo> apiInfoList) {
        for (SysApiInfo apiInfo : apiInfoList) {
            apiInfo.setCreateTime(new Date());
        }
        return this.saveBatch(apiInfoList);
    }

}
