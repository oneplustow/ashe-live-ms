package cn.oneplustow.sc.controller;

import cn.oneplustow.common.annoatation.Log;
import cn.oneplustow.common.domain.AjaxResult;
import cn.oneplustow.common.enume.BusinessType;
import cn.oneplustow.common.web.controller.BaseController;
import cn.oneplustow.common.web.page.TableDataInfo;
import cn.oneplustow.sc.entity.SysApiInfo;
import cn.oneplustow.sc.service.ISysApiInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 系统api信息Controller
 *
 * @author cc
 * @date 2021-06-18
 */
@RestController
@RequestMapping("/system/apiInfo" )
public class SysApiInfoController extends BaseController {
    @Autowired
    private ISysApiInfoService sysApiInfoService;

    /**
     * 查询系统api信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:apiInfo:list')" )
    @GetMapping("/list" )
    public TableDataInfo list(SysApiInfo sysApiInfo) {
        return null;
    }


    /**
     * 获取系统api信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:apiInfo:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(sysApiInfoService.getById(id));
    }

    /**
     * 新增系统api信息
     */
    @PreAuthorize("@ss.hasPermi('system:apiInfo:add')" )
    @Log(title = "系统api信息" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysApiInfo sysApiInfo) {
        return toAjax(sysApiInfoService.save(sysApiInfo) ? 1 : 0);
    }

    /**
     * 修改系统api信息
     */
    @PreAuthorize("@ss.hasPermi('system:apiInfo:edit')" )
    @Log(title = "系统api信息" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysApiInfo sysApiInfo) {
        return toAjax(sysApiInfoService.updateById(sysApiInfo) ? 1 : 0);
    }

    /**
     * 删除系统api信息
     */
    @PreAuthorize("@ss.hasPermi('system:apiInfo:remove')" )
    @Log(title = "系统api信息" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysApiInfoService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }
}
