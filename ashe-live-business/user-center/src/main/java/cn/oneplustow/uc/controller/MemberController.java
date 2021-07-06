package cn.oneplustow.uc.controller;


import cn.oneplustow.common.annoatation.Log;
import cn.oneplustow.common.enume.BusinessType;
import cn.oneplustow.common.web.controller.BaseController;
import cn.oneplustow.common.domain.AjaxResult;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.uc.entity.Member;
import cn.oneplustow.uc.service.IMemberService;
import cn.oneplustow.uc.vo.SaveMemberDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Api(tags = "用户信息控制器")
@RestController
@RequestMapping("/member")
public class MemberController extends BaseController
{
    @Autowired
    private IMemberService memberService;

    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('uc:member:list')")
    @GetMapping("/list")
    public AjaxResult list(Member user)
    {
        PageUtil.startPage();
        List<Member> list = memberService.selectMemberList(user);
        return AjaxResult.success(PageUtil.getDataTable(list));
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('uc:member:query')")
    @GetMapping(value = { "/", "/{userId}" })
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId)
    {
        return AjaxResult.success(memberService.selectMemberById(userId));
    }

    /**
     * 注册用户
     */
    @Log(title = "注册用户", businessType = BusinessType.INSERT)
    @PostMapping("public/register")
    public AjaxResult register(@RequestBody SaveMemberDto saveMemberDto)
    {
        saveMemberDto.setNickName(saveMemberDto.getUserName());
        return toAjax(memberService.insertMember(saveMemberDto));
    }


    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('uc:member:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable List<Long> userIds)
    {
        return toAjax(memberService.deleteMemberByIds(userIds));
    }

}