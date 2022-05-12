package cn.oneplustow.sc.controller;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import cn.oneplustow.common.annoatation.Log;
import cn.oneplustow.common.domain.AjaxResult;
import cn.oneplustow.common.enume.BusinessType;
import cn.oneplustow.common.web.controller.BaseController;
import cn.oneplustow.config.db.model.PageQuery;
import cn.oneplustow.config.db.model.TableDataInfo;
import cn.oneplustow.sc.entity.GenTable;
import cn.oneplustow.sc.entity.GenTableColumn;
import cn.oneplustow.sc.service.IGenTableColumnService;
import cn.oneplustow.sc.service.IGenTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 *
 * @author Lion Li
 */
@Api(value = "代码生成", tags = {"代码生成管理"})
@RestController
@RequestMapping("/tool/gen")
public class GenController extends BaseController {

    @Autowired
    private IGenTableService genTableService;
    @Autowired
    private IGenTableColumnService genTableColumnService;


    /**
     * 查询代码生成列表
     */
    @ApiOperation("查询代码生成列表")
    @PreAuthorize("@ss.hasPermi('tool:gen:list')")
    @GetMapping("/list")
    public AjaxResult genList(GenTable genTable, PageQuery pageQuery) {
        return AjaxResult.success(genTableService.selectPageGenTableList(genTable, pageQuery));
    }

    /**
     * 修改代码生成业务
     */
    @ApiOperation("修改代码生成业务")
    @PreAuthorize("@ss.hasPermi('tool:gen:query')")
    @GetMapping(value = "/{talbleId}")
    public AjaxResult<Map<String, Object>> getInfo(@PathVariable Long talbleId) {
        GenTable table = genTableService.selectGenTableById(talbleId);
        List<GenTable> tables = genTableService.selectGenTableAll();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(talbleId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", table);
        map.put("rows", list);
        map.put("tables", tables);
        return AjaxResult.success(map);
    }

    /**
     * 查询数据库列表
     */
    @ApiOperation("查询数据库列表")
    @PreAuthorize("@ss.hasPermi('tool:gen:list')")
    @GetMapping("/db/list")
    public AjaxResult dataList(GenTable genTable, PageQuery pageQuery) {
        return AjaxResult.success(genTableService.selectPageDbTableList(genTable, pageQuery));
    }

    /**
     * 查询数据表字段列表
     */
    @ApiOperation("查询数据表字段列表")
    @PreAuthorize("@ss.hasPermi('tool:gen:list')")
    @GetMapping(value = "/column/{talbleId}")
    public AjaxResult columnList(Long tableId) {
        TableDataInfo<GenTableColumn> dataInfo = new TableDataInfo<>();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        return AjaxResult.success(TableDataInfo.build(list));
    }

    /**
     * 导入表结构（保存）
     */
    @ApiOperation("导入表结构（保存）")
    @PreAuthorize("@ss.hasPermi('tool:gen:import')")
    @Log(title = "代码生成", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    public AjaxResult<Void> importTableSave(String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return AjaxResult.success();
    }

    /**
     * 修改保存代码生成业务
     */
    @ApiOperation("修改保存代码生成业务")
    @PreAuthorize("@ss.hasPermi('tool:gen:edit')")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult<Void> editSave(@Validated @RequestBody GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return AjaxResult.success();
    }

    /**
     * 删除代码生成
     */
    @ApiOperation("删除代码生成")
    @PreAuthorize("@ss.hasPermi('tool:gen:remove')")
    @Log(title = "代码生成", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tableIds}")
    public AjaxResult<Void> remove(@PathVariable Long[] tableIds) {
        genTableService.deleteGenTableByIds(tableIds);
        return AjaxResult.success();
    }

    /**
     * 预览代码
     */
    @ApiOperation("预览代码")
    @PreAuthorize("@ss.hasPermi('tool:gen:preview')")
    @GetMapping("/preview/{tableId}")
    public AjaxResult<Map<String, String>> preview(@PathVariable("tableId") Long tableId) throws IOException {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return AjaxResult.success(dataMap);
    }

    /**
     * 生成代码（下载方式）
     */
    @ApiOperation("生成代码（下载方式）")
    @PreAuthorize("@ss.hasPermi('tool:gen:code')")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/download/{tableName}")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genTableService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     */
    @ApiOperation("生成代码（自定义路径）")
    @PreAuthorize("@ss.hasPermi('tool:gen:code')")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{tableName}")
    public AjaxResult<Void> genCode(@PathVariable("tableName") String tableName) {
        genTableService.generatorCode(tableName);
        return AjaxResult.success();
    }

    /**
     * 同步数据库
     */
    @ApiOperation("同步数据库")
    @PreAuthorize("@ss.hasPermi('tool:gen:edit')")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @GetMapping("/synchDb/{tableName}")
    public AjaxResult<Void> synchDb(@PathVariable("tableName") String tableName) {
        genTableService.synchDb(tableName);
        return AjaxResult.success();
    }

    /**
     * 批量生成代码
     */
    @ApiOperation("批量生成代码")
    @PreAuthorize("@ss.hasPermi('tool:gen:code')")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.downloadCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IoUtil.write(response.getOutputStream(), false, data);
    }
}
