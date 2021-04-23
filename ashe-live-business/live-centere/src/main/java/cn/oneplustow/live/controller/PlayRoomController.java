package cn.oneplustow.live.controller;


import cn.oneplustow.api.auth.util.SecurityUtils;
import cn.oneplustow.common.annoatation.Log;
import cn.oneplustow.common.enume.BusinessType;
import cn.oneplustow.common.web.controller.BaseController;
import cn.oneplustow.common.web.domain.AjaxResult;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.live.entity.PlayRoom;
import cn.oneplustow.live.service.IPlayRoomService;
import cn.oneplustow.live.vo.QueryPlayRoomDto;
import cn.oneplustow.live.vo.SavePlayRoomDto;
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
    public AjaxResult list(QueryPlayRoomDto playRoom) {
        List<PlayRoom> list = playRoomService.selectPage(playRoom);
        return AjaxResult.success(PageUtil.getDataTable(list));
    }


    /**
     * 获取直播间管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('live:PlayRoom:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(playRoomService.getPlayRoomById(id));
    }

    /**
     * 新增直播间
     */
    @PreAuthorize("@ss.hasPermi('live:PlayRoom:add')" )
    @Log(title = "直播间管理" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SavePlayRoomDto savePlayRoomDto) {
        return toAjax(playRoomService.savePlayRoom(savePlayRoomDto));
    }

    /**
     * 开通直播间
     */
    @PreAuthorize("@ss.hasPermi('live:PlayRoom:add')" )
    @Log(title = "直播间管理" , businessType = BusinessType.INSERT)
    @PostMapping("/openUp")
    public AjaxResult openUp(String name) {
        return toAjax(playRoomService.openUp(name,SecurityUtils.getUserId()));
    }

    /**
     * 修改直播间管理
     */
    @PreAuthorize("@ss.hasPermi('live:PlayRoom:edit')" )
    @Log(title = "直播间管理" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SavePlayRoomDto savePlayRoomDto) {
        return toAjax(playRoomService.savePlayRoom(savePlayRoomDto));
    }

    /**
     * 删除直播间管理
     */
    @PreAuthorize("@ss.hasPermi('live:PlayRoom:remove')" )
    @Log(title = "直播间管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(playRoomService.delPlayRoom(Arrays.asList(ids)));
    }
}
