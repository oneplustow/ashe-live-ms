package cn.oneplustow.api.sc.service.fallback;

import cn.oneplustow.api.sc.model.SysDictDataModel;
import cn.oneplustow.api.sc.service.DictDataService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class DictDataServiceFallback implements FallbackFactory<DictDataService> {
    @Override
    public DictDataService create(Throwable throwable) {
        return new DictDataService(){
            @Override
            public List<SysDictDataModel> selectDictDataByType(String dictType) {
                return new ArrayList<>();
            }
        };
    }
}
