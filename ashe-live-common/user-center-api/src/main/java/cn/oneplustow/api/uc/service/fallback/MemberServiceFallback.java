package cn.oneplustow.api.uc.service.fallback;

import cn.oneplustow.api.uc.service.MemberService;
import cn.oneplustow.api.uc.service.model.MemberModel;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class MemberServiceFallback implements FallbackFactory<MemberService> {
    @Override
    public MemberService create(Throwable throwable) {
        return new MemberService(){
            @Override
            public MemberModel getMemberByName(String memberName) {
                return new MemberModel();
            }
        };
    }
}
