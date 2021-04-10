package cn.oneplustow.live.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.oneplustow.common.annoatation.Log;
import cn.oneplustow.common.enume.BusinessType;
import cn.oneplustow.common.web.controller.BaseController;
import cn.oneplustow.common.web.domain.AjaxResult;
import cn.oneplustow.common.web.page.TableDataInfo;
import cn.oneplustow.config.db.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.oneplustow.live.entity.StreamServer;
import cn.oneplustow.live.service.IStreamServerService;

/**
 * 流服务器信息Controller
 *
 * @author cc
 * @date 2021-04-09
 */
@RestController
@RequestMapping("/SteamServer" )
public class StreamServerController extends BaseController {
    @Autowired
    private IStreamServerService streamServerService;

    /**
     * 查询流服务器信息列表
     */
    @PreAuthorize("@ss.hasPermi('live:SteamServer:list')" )
    @GetMapping("/list" )
    public AjaxResult list(StreamServer streamServer) {
        List<StreamServer> list = streamServerService.page(streamServer);
        return AjaxResult.success(PageUtil.getDataTable(list));
    }


    /**
     * 获取流服务器信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('live:SteamServer:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Integer id) {
        return AjaxResult.success(streamServerService.getById(id));
    }

    /**
     * 新增流服务器信息
     */
    @PreAuthorize("@ss.hasPermi('live:SteamServer:add')" )
    @Log(title = "流服务器信息" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StreamServer streamServer) {
        return toAjax(streamServerService.save(streamServer) ? 1 : 0);
    }

    /**
     * 修改流服务器信息
     */
    @PreAuthorize("@ss.hasPermi('live:SteamServer:edit')" )
    @Log(title = "流服务器信息" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StreamServer streamServer) {
        return toAjax(streamServerService.updateById(streamServer) ? 1 : 0);
    }

    /**
     * 删除流服务器信息
     */
    @PreAuthorize("@ss.hasPermi('live:SteamServer:remove')" )
    @Log(title = "流服务器信息" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Integer[] ids) {
        return toAjax(streamServerService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }
}
