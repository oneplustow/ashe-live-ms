package cn.oneplustow.user.controller;
import cn.oneplustow.api.auth.util.SecurityUtils;
import cn.oneplustow.common.annoatation.Log;
import cn.oneplustow.common.enume.BusinessType;
import cn.oneplustow.common.constant.UserConstants;
import cn.oneplustow.common.web.controller.BaseController;
import cn.oneplustow.common.web.domain.AjaxResult;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.user.entity.SysConfig;
import cn.oneplustow.user.service.ISysConfigService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 参数配置 信息操作处理
 * 
 * @author ruoyi
 */
@Api(tags = "参数配置")
@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController
{
    @Autowired
    private ISysConfigService configService;

    /**
     * 获取参数配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:config:list')")
    @GetMapping("/list")
    public AjaxResult list(SysConfig config)
    {
        PageUtil.startPage();
        List<SysConfig> list = configService.selectConfigList(config);
        return AjaxResult.success(PageUtil.getDataTable(list));
    }

//    @Log(title = "参数管理", businessType = BusinessType.EXPORT)
//    @PreAuthorize("@ss.hasPermi('system:config:export')")
//    @GetMapping("/export")
//    public AjaxResult export(SysConfig config)
//    {
//        List<SysConfig> list = configService.selectConfigList(config);
//        ExcelUtil<SysConfig> util = new ExcelUtil<SysConfig>(SysConfig.class);
//        return util.exportExcel(list, "参数数据");
//    }

    /**
     * 根据参数编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:config:query')")
    @GetMapping(value = "/{configId}")
    public AjaxResult getInfo(@PathVariable Long configId)
    {
        return AjaxResult.success(configService.selectConfigById(configId));
    }

    /**
     * 根据参数键名查询参数值
     */
    @GetMapping(value = "/configKey/{configKey}")
    public AjaxResult getConfigKey(@PathVariable String configKey)
    {
        return AjaxResult.success(configService.selectConfigByKey(configKey));
    }

    /**
     * 新增参数配置
     */
    @PreAuthorize("@ss.hasPermi('system:config:add')")
    @Log(title = "参数管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysConfig config)
    {
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config)))
        {
            return AjaxResult.error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        config.setCreateBy(SecurityUtils.getUsername());
        return toAjax(configService.insertConfig(config));
    }

    /**
     * 修改参数配置
     */
    @PreAuthorize("@ss.hasPermi('system:config:edit')")
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysConfig config)
    {
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config)))
        {
            return AjaxResult.error("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        config.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(configService.updateConfig(config));
    }

    /**
     * 删除参数配置
     */
    @PreAuthorize("@ss.hasPermi('system:config:remove')")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{configIds}")
    public AjaxResult remove(@PathVariable Long[] configIds)
    {
        return toAjax(configService.deleteConfigByIds(configIds));
    }
}
