package cn.oneplustow.common.verify;

import cn.hutool.core.collection.CollUtil;
import cn.oneplustow.common.exception.ParameterMissingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;


/**
 *
 * javaBean 验证上下文
 * 能够对javaBean进行属性验证
 *
 * @author cc
 */
@Component
public class ValidatorContext {

    private Validator validator;

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }


    /**
     * 对JavaBean进验证，进行默认(Defautl)分组验证
     * @param obj 需要验证的JavaBean
     * @return 成功返回true
     * @exception 验证错误会抛出ParameterException异常，消息为第一个验证错误的信息
     */
    public boolean validate(Object obj){
        return validate(obj, Default.class);
    }

    /**
     * 对JavaBean进验证，进行指定分组验证
     * @param obj 需要验证的JavaBean
     * @return 成功返回true
     * @exception 验证错误会抛出ParameterException异常，消息为第一个验证错误的信息
     */
    public boolean validate(Object obj,Class ...classes){
        Set<ConstraintViolation<Object>> validates =
                validator.validate(obj, classes);
        if (CollUtil.isNotEmpty(validates)) {
            for (ConstraintViolation<Object> validate : validates) {
                throw new ParameterMissingException(validate.getMessage());
            }
        }
        return true;
    }

    /**
     * 对JavaBean尝试进行默认(Defautl)分组验证
     * @param obj 需要验证的JavaBean
     * @return 成功返回true，失败返回false
     */
    public boolean tryValidate(Object obj){
        return tryValidate(obj,Default.class);
    }

    /**
     * 对JavaBean尝试进行指定分组验证
     * @param obj 需要验证的JavaBean
     * @return 成功返回true，失败返回false
     */
    public boolean tryValidate(Object obj,Class ...classes){
        Set<ConstraintViolation<Object>> validates =
                validator.validate(obj, classes);
        return CollUtil.isNotEmpty(validates);
    }
}
