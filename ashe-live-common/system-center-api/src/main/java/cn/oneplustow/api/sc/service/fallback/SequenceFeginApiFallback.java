package cn.oneplustow.api.sc.service.fallback;

import cn.oneplustow.api.sc.service.SequenceFeginApi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class SequenceFeginApiFallback implements FallbackFactory<SequenceFeginApi> {
    @Override
    public SequenceFeginApi create(Throwable throwable) {
        return new SequenceFeginApi(){
            @Override
            public String apply(String seqName) {
                return null;
            }
        };
    }
}
