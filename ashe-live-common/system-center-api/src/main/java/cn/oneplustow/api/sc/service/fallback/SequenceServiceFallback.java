package cn.oneplustow.api.sc.service.fallback;

import cn.oneplustow.api.sc.service.SequenceService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class SequenceServiceFallback implements FallbackFactory<SequenceService> {
    @Override
    public SequenceService create(Throwable throwable) {
        return new SequenceService(){
            @Override
            public String apply(String seqName) {
                return null;
            }
        };
    }
}
