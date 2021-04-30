package cn.oneplustow.lc.controller;


import cn.oneplustow.api.ac.util.SecurityUtils;
import cn.oneplustow.common.annoatation.Log;
import cn.oneplustow.common.enume.BusinessType;
import cn.oneplustow.common.web.controller.BaseController;
import cn.oneplustow.common.web.domain.AjaxResult;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.lc.entity.PlayRoom;
import cn.oneplustow.lc.service.IPlayRoomService;
import cn.oneplustow.lc.vo.QueryPlayRoomDto;
import cn.oneplustow.lc.vo.SavePlayRoomDto;
import io.swagger.annotations.Api;
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
@Api(tags = "直播间管理")
@RestController
@RequestMapping("/playRoom" )
public class PlayRoomController extends BaseController {
    @Autowired
    private IPlayRoomService playRoomService;

    /**
     * 查询直播间管理列表
     */
    @PreAuthorize("@ss.hasPermi('lc:playRoom:list')" )
    @GetMapping("/list" )
    public AjaxResult list(QueryPlayRoomDto playRoom) {
        List<PlayRoom> list = playRoomService.selectPage(playRoom);
        return AjaxResult.success(PageUtil.getDataTable(list));
    }


    /**
     * 获取直播间管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('lc:playRoom:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(playRoomService.getPlayRoomDetailVoByIdOrUserId(id,null));
    }

    /**
     * 获取当前登录用户直播间详细信息
     */
    @PreAuthorize("@ss.hasPermi('lc:playRoom:query')" )
    @GetMapping(value = "/getUserPlayRoom" )
    public AjaxResult getInfoByUserId() {
        return AjaxResult.success(playRoomService.getPlayRoomDetailVoByIdOrUserId(null,SecurityUtils.getUserId()));
    }

    /**
     * 新增直播间
     */
    @PreAuthorize("@ss.hasPermi('lc:playRoom:add')" )
    @Log(title = "新增直播间" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SavePlayRoomDto savePlayRoomDto) {
        return toAjax(playRoomService.savePlayRoom(savePlayRoomDto));
    }

    /**
     * 开通直播间
     */
    @PreAuthorize("@ss.hasPermi('lc:playRoom:add')" )
    @Log(title = "开通直播间" , businessType = BusinessType.INSERT)
    @PostMapping("/openUp")
    public AjaxResult openUp(String name) {
        return toAjax(playRoomService.openUp(name,SecurityUtils.getUserId()));
    }

    /**
     * 开通直播间
     */
    @PreAuthorize("@ss.hasPermi('lc:playRoom:add')" )
    @Log(title = "开始直播" , businessType = BusinessType.INSERT)
    @PostMapping("/startLive")
    public AjaxResult startLive() {
        return AjaxResult.success(playRoomService.startLive(SecurityUtils.getUserId()));
    }


    /**
     * 修改直播间
     */
    @PreAuthorize("@ss.hasPermi('lc:playRoom:edit')" )
    @Log(title = "修改直播间" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SavePlayRoomDto savePlayRoomDto) {
        return toAjax(playRoomService.savePlayRoom(savePlayRoomDto));
    }

    /**
     * 删除直播间
     */
    @PreAuthorize("@ss.hasPermi('lc:playRoom:remove')" )
    @Log(title = "删除直播间" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(playRoomService.delPlayRoom(Arrays.asList(ids)));
    }
}