package cn.oneplustow.uc.controller;


import cn.oneplustow.api.ac.util.SecurityUtils;
import cn.oneplustow.common.annoatation.Log;
import cn.oneplustow.common.enume.BusinessType;
import cn.oneplustow.common.web.controller.BaseController;
import cn.oneplustow.common.web.domain.AjaxResult;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.uc.entity.Member;
import cn.oneplustow.uc.service.IMemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('uc:member:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Member user)
    {
        user.setCreateBy(SecurityUtils.getUsername());

        return toAjax(memberService.insertMember(user));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('uc:member:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Member user)
    {
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(memberService.updateMember(user));
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

    /**
     * 重置密码
     */
    @PreAuthorize("@ss.hasPermi('uc:member:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    public AjaxResult resetPwd(String id,String password)
    {
        return toAjax(memberService.resetMemberPwd(id,password));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('uc:member:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    public AjaxResult changeStatus(String id,String status)
    {
        return toAjax(memberService.updateMemberStatus(id,status));
    }
}