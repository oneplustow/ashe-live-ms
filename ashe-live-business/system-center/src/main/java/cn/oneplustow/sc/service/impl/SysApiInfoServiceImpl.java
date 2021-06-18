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
    public boolean init() {
        List<SysApiInfo> apiInfoList = generateSysApiInfo();
        this.saveApiInfo(apiInfoList);
        return true;
    }

    @Override
    public void saveApiInfo(List<SysApiInfo> apiInfoList) {
        for (SysApiInfo apiInfo : apiInfoList) {
            apiInfo.setCreateTime(new Date());
        }
        this.saveBatch(apiInfoList);
    }

    private List<SysApiInfo> generateSysApiInfo(){
        //获取所有的RequestMapping
        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext,
                HandlerMapping.class, true, false);
        Collection<HandlerMapping> values = allRequestMappings.values();
        for (HandlerMapping handlerMapping : values) {
            //本项目只需要RequestMappingHandlerMapping中的URL映射
            if (handlerMapping instanceof RequestMappingHandlerMapping){
                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
                return parseRequestMappingHandlerMapping(requestMappingHandlerMapping);
            }
        }
        return null;
    }

    private List<SysApiInfo> parseRequestMappingHandlerMapping(RequestMappingHandlerMapping requestMappingHandlerMapping){
        //从request 类型的 请求处理映射器里面拿到 映射器方法
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        List<SysApiInfo> apiInfoList = new ArrayList<>();
        //循环遍历每一个 请求映射信息
        for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()){
            SysApiInfo apiInfo = new SysApiInfo();
            //key是请求映射信息，包括请求方法，参数类型，请求url等信息
            RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
            //value是处理方法，包括方法对象，class对象等
            HandlerMethod mappingInfoValue = requestMappingInfoHandlerMethodEntry.getValue();
            //拿到controller的类信息 判断是否属于我们的包下面
            Class<?> controller = mappingInfoValue.getBeanType();
            if (!StrUtil.startWith(controller.getName(),"cn.oneplustow")) {continue;}
            Method method = mappingInfoValue.getMethod();
            //拿到权限字符串
            PreAuthorize annotation = method.getAnnotation(PreAuthorize.class);
            if(annotation != null) {
                String permissionString = annotation.value();
                permissionString = StrUtil.subAfter(permissionString, "('", false);
                permissionString = StrUtil.subBefore(permissionString, "')", false);
                apiInfo.setPermission(permissionString);
            }
            //StrUtil.isWrap()
            //拿到请求方法类型 比如GET POST等
            RequestMethodsRequestCondition methodCondition = requestMappingInfo.getMethodsCondition();
            String requestType = CollUtil.getFirst(methodCondition.getMethods()).name();
            apiInfo.setType(requestType);
            //拿到请求url
            PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
            String requestUrl = CollUtil.getFirst(patternsCondition.getPatterns());
            apiInfo.setUrl(requestUrl);
            Class<?>[] methodParamTypes = mappingInfoValue.getMethod().getParameterTypes();
            apiInfo.setSystemFlag(systemFlag);
            apiInfoList.add(apiInfo);
        }
        return apiInfoList;
    }
}
