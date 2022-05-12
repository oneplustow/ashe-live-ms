package cn.oneplustow.api.sc.service.fallback;

import cn.oneplustow.api.sc.model.DictDataResp;
import cn.oneplustow.api.sc.service.DictDataFeginApi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class DictDataFeginApiFallback implements FallbackFactory<DictDataFeginApi> {
    @Override
    public DictDataFeginApi create(Throwable throwable) {
        return new DictDataFeginApi(){
            @Override
            public List<DictDataResp> selectDictDataByType(String dictType) {
                return new ArrayList<>();
            }
        };
    }
}
