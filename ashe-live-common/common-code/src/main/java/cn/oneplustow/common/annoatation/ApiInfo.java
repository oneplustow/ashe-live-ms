package cn.oneplustow.common.annoatation;

import java.lang.annotation.*;

/**
 * @author CC
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiInfo {
    String name();

    String[] permission() default "";


}
