package cn.oneplustow.uc.open;


import cn.hutool.core.util.ObjectUtil;
import cn.oneplustow.uc.entity.Member;
import cn.oneplustow.uc.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/fegin/member")
public class MemberOpen
{
    @Autowired
    private IMemberService memberService;

    /**
     * 根据用户编号获取详细信息
     */
    @GetMapping("getMemberByName")
    public Member getMemberByName(@RequestParam("memberName") String memberName)
    {
        if (ObjectUtil.isNotNull(memberName))
        {
            return memberService.selectMemberByMemberName(memberName);
        }
        return null;
    }
}