package cn.oneplustow.api.ac.aspectj;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.api.ac.model.LoginUser;
import cn.oneplustow.api.ac.util.SecurityUtils;
import cn.oneplustow.api.sc.model.SysRoleModel;
import cn.oneplustow.api.sc.model.UserResp;
import cn.oneplustow.common.constant.Constants;
import cn.oneplustow.api.ac.annoatation.ArgsInject;
import org.apache.commons.jxpath.JXPathContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author cc
 */
@Component
@Aspect
public class ArgsInjectAspect {

    @Pointcut("@annotation(cn.oneplustow.api.ac.annoatation.ArgsInject)")
    public void annotationAspect(){}

    @Around("annotationAspect()")
    public Object injectUserId(ProceedingJoinPoint pjp) throws Throwable{
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //获取方法参数值
        Object[] args = pjp.getArgs();
        //获取当前登录用户
        UserResp user = loginUser.getUser();
        //获取方法签名
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        //获取方法上的ArgsInject注解
        ArgsInject argsInject = signature.getMethod().getAnnotation(ArgsInject.class);
        //获取需要注入的角色
        String[] roleNames = argsInject.roleNames();

        String oneselfData = getOneselfData(user.getRoles());
        //获取ArgsInject注解的参数Jxpath
        String jxpathString = getJxpath(oneselfData, argsInject);
        if(StrUtil.isNotBlank(jxpathString)){
            //获取方法的参数名称
            String[] parameterNames = signature.getParameterNames();
            //取出前缀部分定位到需要修改的对象
            String argsName = StrUtil.subBefore(jxpathString, "/",true);
            //取出后缀Jxpath
            jxpathString = StrUtil.subAfter(jxpathString, "/",true);
            //找到需要修改的参数所在下标
            int argsIndex = ArrayUtil.indexOf(parameterNames, argsName);
            //获取修改后的值
            Object value = getDataScopeVluae(oneselfData,user);
            //如果是基本类型则直接修改值，如果是JavaBean则使用Jxpath修改值
            if(StrUtil.isBlank(jxpathString)){
                args[argsIndex] = value;
            }else{
                Object argsObject = args[argsIndex];
                JXPathContext jxpath = JXPathContext.newContext(argsObject);
                jxpath.setValue(jxpathString,value);
            }
        }
        try {
            //用修改后的值执行方法
            return pjp.proceed(args);
        } catch (Throwable throwable) {
            throw throwable;
        }
    }

    private Object getDataScopeVluae(String dataScope, UserResp user) {
        if (Constants.DataScope.仅本人.equalsIgnoreCase(dataScope)) {
            return user.getUserId();
        }
        if(Constants.DataScope.本销控区.equalsIgnoreCase(dataScope)){
            return user.getPinControl();
        }
        return null;
    }

    private String getJxpath(String dataScope, ArgsInject argsInject){
        if (Constants.DataScope.仅本人.equalsIgnoreCase(dataScope)) {
            return argsInject.userIdJxpath();
        }
        if(Constants.DataScope.本销控区.equalsIgnoreCase(dataScope)){
            return argsInject.pinControlJxpath();
        }
        return null;
    }

    private String getOneselfData(List<SysRoleModel> roles) {
        if(CollUtil.isEmpty(roles)){
            return null;
        }
        for (SysRoleModel role : roles) {
            if (Constants.DataScope.仅本人.equalsIgnoreCase(role.getDataScope())) {
                return Constants.DataScope.仅本人;
            }
            if(Constants.DataScope.本销控区.equalsIgnoreCase(role.getDataScope())){
                return Constants.DataScope.本销控区;
            }
        }
        return null;
    }


    public void againAssign(){

    }
}
