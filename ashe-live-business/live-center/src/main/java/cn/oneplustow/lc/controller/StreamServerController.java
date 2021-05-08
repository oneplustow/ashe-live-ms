package cn.oneplustow.lc.controller;

import cn.oneplustow.common.annoatation.Log;
import cn.oneplustow.common.domain.AjaxResult;
import cn.oneplustow.common.enume.BusinessType;
import cn.oneplustow.common.web.controller.BaseController;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.lc.entity.StreamServer;
import cn.oneplustow.lc.service.IStreamServerService;
import cn.oneplustow.lc.vo.QueryStreamServerDto;
import cn.oneplustow.lc.vo.SaveStreamServerDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 流服务器信息Controller
 *
 * @author cc
 * @date 2021-04-09
 */
@Api(tags = "流服务器信息")
@RestController
@RequestMapping("/streamServer" )
public class StreamServerController extends BaseController {
    @Autowired
    private IStreamServerService streamServerService;

    /**
     * 查询流服务器信息列表
     */
    @PreAuthorize("@ss.hasPermi('lc:streamServer:list')" )
    @GetMapping("/list" )
    public AjaxResult list(QueryStreamServerDto queryStreamServerDto) {
        List<StreamServer> list = streamServerService.page(queryStreamServerDto);
        return AjaxResult.success(PageUtil.getDataTable(list));
    }


    /**
     * 获取流服务器信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('lc:streamServer:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(streamServerService.getStreamServerById(id));
    }

    /**
     * 新增流服务器信息
     */
    @PreAuthorize("@ss.hasPermi('lc:streamServer:add')" )
    @Log(title = "流服务器信息" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SaveStreamServerDto streamServer) {
        return toAjax(streamServerService.addStreamServer(streamServer));
    }

    /**
     * 修改流服务器信息
     */
    @PreAuthorize("@ss.hasPermi('lc:streamServer:edit')" )
    @Log(title = "流服务器信息" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SaveStreamServerDto streamServer) {
        return toAjax(streamServerService.updateStreamServer(streamServer));
    }

    /**
     * 删除流服务器信息
     */
    @PreAuthorize("@ss.hasPermi('lc:streamServer:remove')" )
    @Log(title = "流服务器信息" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Integer[] ids) {
        return toAjax(streamServerService.delById(Arrays.asList(ids)));
    }

    /**
     * 测试连接流服务器
     */
    @Log(title = "测试连接流服务器" , businessType = BusinessType.QUERY)
    @PostMapping("/connection" )
    public AjaxResult connection(@RequestBody SaveStreamServerDto streamServerDto) {
        return AjaxResult.success(streamServerService.heartBeatDetectionById(streamServerDto.getId()));
    }
}
