package cn.oneplustow.api.uc.service.fallback;

import cn.oneplustow.api.fallback.BaseFallbackFactory;
import cn.oneplustow.api.uc.model.MemberModel;
import cn.oneplustow.api.uc.service.MemberService;
import org.springframework.stereotype.Component;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class MemberServiceFallback extends BaseFallbackFactory<MemberService> {
    @Override
    public MemberService doCreate(Throwable throwable) {
        return new MemberService(){
            @Override
            public MemberModel getMemberByName(String memberName) {
                return new MemberModel();
            }
        };
    }
}
