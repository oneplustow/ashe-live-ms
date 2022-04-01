package cn.oneplustow.api.sc.service.fallback;

import cn.oneplustow.api.fallback.BaseFallbackFactory;
import cn.oneplustow.api.sc.model.SimpleUser;
import cn.oneplustow.api.sc.model.UserResp;
import cn.oneplustow.api.sc.service.UserFeginApi;
import cn.oneplustow.api.sc.vo.SaveUserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author cc
 * @date 16/09/2020 11:16
 */
@Component
public class UserFeginApiFallback extends BaseFallbackFactory<UserFeginApi> {
    @Override
    public UserFeginApi doCreate(Throwable throwable) {
        return new UserFeginApi(){
            @Override
            public UserResp getUserByName(String username) {
                return new UserResp();
            }

            @Override
            public List<SimpleUser> getSimpleUserListById(Set<String> userIdSet) {
                return new ArrayList<>();
            }

            @Override
            public Long saveMemberUser(SaveUserDto saveUserDto) {
                return null;
            }
        };
    }
}
