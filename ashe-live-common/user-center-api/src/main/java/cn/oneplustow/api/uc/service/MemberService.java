package cn.oneplustow.api.uc.service;

import cn.oneplustow.api.uc.service.fallback.MemberServiceFallback;
import cn.oneplustow.api.uc.service.model.MemberModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author cc
 * @date 16/09/2020 11:03
 */
@FeignClient(value = "user-center",fallbackFactory = MemberServiceFallback.class)
@RequestMapping("user-center/fegin/member")
public interface MemberService {

    /**
     * 获取用户信息
     * @param memberName
     * @return
     */
    @GetMapping("getmemberByName")
    MemberModel getMemberByName(@RequestParam("memberName") String memberName);

}
