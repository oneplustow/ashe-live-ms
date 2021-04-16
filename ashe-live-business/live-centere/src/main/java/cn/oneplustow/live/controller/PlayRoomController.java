package cn.oneplustow.live.controller;


import cn.hutool.core.util.StrUtil;
import cn.oneplustow.common.annoatation.Log;
import cn.oneplustow.common.enume.BusinessType;
import cn.oneplustow.common.web.controller.BaseController;
import cn.oneplustow.common.web.domain.AjaxResult;
import cn.oneplustow.common.web.page.TableDataInfo;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.live.entity.PlayRoom;
import cn.oneplustow.live.service.IPlayRoomService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 直播间管理Controller
 *
 * @author cc
 * @date 2021-04-14
 */
@RestController
@RequestMapping("/playRoom" )
public class PlayRoomController extends BaseController {
    @Autowired
    private IPlayRoomService playRoomService;

    /**
     * 查询直播间管理列表
     */
    @PreAuthorize("@ss.hasPermi('live:PlayRoom:list')" )
    @GetMapping("/list" )
    public AjaxResult list(PlayRoom playRoom) {
        List<PlayRoom> list = playRoomService.selectPage(playRoom);
        return AjaxResult.success(PageUtil.getDataTable(list));
    }


    /**
     * 获取直播间管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('live:PlayRoom:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(playRoomService.getById(id));
    }

    /**
     * 新增直播间管理
     */
    @PreAuthorize("@ss.hasPermi('live:PlayRoom:add')" )
    @Log(title = "直播间管理" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlayRoom playRoom) {
        return toAjax(playRoomService.save(playRoom) ? 1 : 0);
    }

    /**
     * 修改直播间管理
     */
    @PreAuthorize("@ss.hasPermi('live:PlayRoom:edit')" )
    @Log(title = "直播间管理" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlayRoom playRoom) {
        return toAjax(playRoomService.updateById(playRoom) ? 1 : 0);
    }

    /**
     * 删除直播间管理
     */
    @PreAuthorize("@ss.hasPermi('live:PlayRoom:remove')" )
    @Log(title = "直播间管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(playRoomService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }
}
