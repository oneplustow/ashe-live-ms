package cn.oneplustow.api.user.service.fallback;

import cn.oneplustow.api.user.model.SysDictDataModel;
import cn.oneplustow.api.user.model.SysUserModel;
import cn.oneplustow.api.user.service.DictDataService;
import cn.oneplustow.api.user.service.UserService;
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
