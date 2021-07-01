package cn.oneplustow.api.ac.annoatation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author cc
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ArgsInject {

    /**
     * 用jxpath语法描述需要注入到的对象属性
     * @return
     */
    public String userIdJxpath() default "";

    public String pinControlJxpath() default "";

    public String[] roleNames() default "";
}
