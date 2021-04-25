package cn.oneplustow.live.controller;


import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.annoatation.Log;
import cn.oneplustow.common.enume.BusinessType;
import cn.oneplustow.common.web.controller.BaseController;
import cn.oneplustow.common.web.domain.AjaxResult;
import cn.oneplustow.common.web.page.TableDataInfo;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.live.entity.PlayRecord;
import cn.oneplustow.live.service.IPlayRecordService;
import cn.oneplustow.live.vo.QueryPlayRoomDto;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 直播记录Controller
 *
 * @author cc
 * @date 2021-04-14
 */
@Api(tags = "直播记录")
@RestController
@RequestMapping("/playRecord" )
public class PlayRecordController extends BaseController {
    @Autowired
    private IPlayRecordService playRecordService;

    /**
     * 查询直播记录列表
     */
    @PreAuthorize("@ss.hasPermi('live:PlayRecord:list')" )
    @GetMapping("/list" )
    public AjaxResult list(PlayRecord playRecord) {
        List<PlayRecord> list = playRecordService.page(playRecord);
        return AjaxResult.success(PageUtil.getDataTable(list));
    }



    /**
     * 获取直播记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('live:PlayRecord:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(playRecordService.getById(id));
    }

    /**
     * 新增直播记录
     */
    @PreAuthorize("@ss.hasPermi('live:PlayRecord:add')" )
    @Log(title = "直播记录" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlayRecord playRecord) {
        return toAjax(playRecordService.save(playRecord) ? 1 : 0);
    }

    /**
     * 修改直播记录
     */
    @PreAuthorize("@ss.hasPermi('live:PlayRecord:edit')" )
    @Log(title = "直播记录" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlayRecord playRecord) {
        return toAjax(playRecordService.updateById(playRecord) ? 1 : 0);
    }

    /**
     * 删除直播记录
     */
    @PreAuthorize("@ss.hasPermi('live:PlayRecord:remove')" )
    @Log(title = "直播记录" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(playRecordService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }
}
