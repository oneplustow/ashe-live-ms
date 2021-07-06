package cn.oneplustow.api.fallback;

import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.exception.WarningMessageException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import feign.FeignException;
import feign.hystrix.FallbackFactory;

/**
 * @author CC
 * @title: BaseFallbackFactory
 * @projectName ashe-live-ms
 * @description:
 * @date 2021/7/323:12
 */
public abstract class BaseFallbackFactory<T> implements FallbackFactory<T> {
    @Override
    public T create(Throwable throwable) {
        //判断异常是否是FeignException
        if (throwable instanceof FeignException) {
            FeignException feignException = (FeignException) throwable;
            //这里非常贴心了提供了一个contentUTF8方法 可以直接将body转成String
            String contentString = feignException.contentUTF8();

            if(StrUtil.isNotBlank(contentString)){
                //判断是否是json类型的字符串
                if (JSONValidator.from(contentString).validate()) {
                    //拿到json里面的msg信息 然后抛出我们自己的异常
                    String message = JSONObject.parseObject(contentString).getString("msg");
                    throw new WarningMessageException(message);
                }
            }
        }
        //调用子类实现的抽象方法
        return doCreate(throwable);
    }

    /**
     *  Returns an instance of the fallback appropriate for the given cause
     * @param throwable
     * @return
     */
    public abstract T doCreate(Throwable throwable);
}
