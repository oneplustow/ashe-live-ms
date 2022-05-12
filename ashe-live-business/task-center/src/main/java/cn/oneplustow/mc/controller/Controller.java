package cn.oneplustow.mc.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.web.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.ServletContext;
import java.util.Collection;
import java.util.Map;

/**
 * 测试
 *
 * @author ruoyi
 */
@Api(tags = "测试")
@RestController
@RequestMapping("/test")
public class Controller extends BaseController {

    @Autowired
    private WebApplicationContext webApplicationContext;
    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('uc:member:list')")
    @GetMapping("/test1/{path}")
    public void test(@PathVariable String path){


    }

    public String testStr(String body){
        return body;
    }

}
