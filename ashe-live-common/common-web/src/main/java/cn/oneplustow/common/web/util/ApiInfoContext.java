package cn.oneplustow.common.web.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.api.sc.model.SysApiInfoModel;
import cn.oneplustow.api.sc.service.ApiInfoService;
import cn.oneplustow.common.annoatation.ApiInfo;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author CC
 */
@Component
public class ApiInfoContext {

    @Value("${spring.application.name}")
    private String systemFlag;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApiInfoService apiInfoService;


    public Boolean init() {
        List<SysApiInfoModel> apiInfoList = generateSysApiInfo();
        apiInfoService.saveApiInfo(apiInfoList);
        return true;
    }

    public List<SysApiInfoModel> generateSysApiInfo(){
        //获取所有的RequestMapping
        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(
                applicationContext,HandlerMapping.class, true, false);
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

    private List<SysApiInfoModel> parseRequestMappingHandlerMapping(RequestMappingHandlerMapping requestMappingHandlerMapping){
        //从request 类型的 请求处理映射器里面拿到 映射器方法
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        List<SysApiInfoModel> apiInfoList = new ArrayList<>();
        //循环遍历每一个 请求映射信息
        for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()){
            SysApiInfoModel apiInfo = new SysApiInfoModel();
            //key是请求映射信息，包括请求方法，参数类型，请求url等信息
            RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
            //value是处理方法，包括方法对象，class对象等
            HandlerMethod mappingInfoValue = requestMappingInfoHandlerMethodEntry.getValue();
            //拿到controller的类信息 判断是否属于我们的包下面
            Class<?> controller = mappingInfoValue.getBeanType();
            ApiInfo apiInfoClass = controller.getAnnotation(ApiInfo.class);
            if(apiInfoClass == null){continue;}
            Method method = mappingInfoValue.getMethod();
            //拿到权限字符串
            ApiInfo annotation = method.getAnnotation(ApiInfo.class);
            if(annotation != null) {
                String[] permissions = annotation.permission();
                String permissionString = StrUtil.join(",", permissions);
//                permissionString = StrUtil.subAfter(permissionString, "('", false);
//                permissionString = StrUtil.subBefore(permissionString, "')", false);
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
