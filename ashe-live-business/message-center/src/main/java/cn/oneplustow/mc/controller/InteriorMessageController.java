package cn.oneplustow.mc.controller;


import cn.oneplustow.api.ac.util.SecurityUtils;
import cn.oneplustow.common.annoatation.Log;
import cn.oneplustow.common.domain.AjaxResult;
import cn.oneplustow.common.enume.BusinessType;
import cn.oneplustow.common.web.controller.BaseController;
import cn.oneplustow.common.web.util.ExcelUtil;
import cn.oneplustow.config.db.model.TableDataInfo;
import cn.oneplustow.mc.entity.criteria.InteriorMessageCriteria;
import cn.oneplustow.mc.entity.criteria.InteriorMessageListCriteria;
import cn.oneplustow.mc.entity.vo.InteriorMessageVo;
import cn.oneplustow.mc.service.IInteriorMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 站内信Controller
 *
 * @author cc
 * @date 2021-10-02
 */
@Validated
@Api(value = "站内信控制器", tags = {"站内信管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/interiorMessage")
public class InteriorMessageController extends BaseController {

    private final IInteriorMessageService iInteriorMessageService;

	/**
	 * 设置为已读消息
	 */
	@ApiOperation("设置为已读消息")
	@GetMapping("/read")
	public AjaxResult read(@ApiParam("站内信ID") @RequestParam List<Integer> id) {
		Long userId = SecurityUtils.getUserId();
		iInteriorMessageService.read(userId,id);
		return AjaxResult.success("已读成功");
	}

	/**
	 * 获取当前用户站内信列表
	 */
    @ApiOperation("获取当前用户站内信列表")
	@GetMapping("/currentuserList")
	public AjaxResult currentuserList(InteriorMessageCriteria criteria) {
		criteria.setToUser(SecurityUtils.getUserId());
		return AjaxResult.success(iInteriorMessageService.list(criteria));
	}


    /**
     * 查询站内信列表
     */
    @ApiOperation("查询站内信列表")
    @PreAuthorize("@ss.hasPermi('journal:interiorMessage:list')")
    @GetMapping("/list")
    public TableDataInfo<InteriorMessageVo> list(@Validated InteriorMessageListCriteria bo) {
        return iInteriorMessageService.queryPageList(bo);
    }


    /**
     * 获取站内信详细信息
     */
    @ApiOperation("获取站内信详细信息")
    @PreAuthorize("@ss.hasPermi('journal:interiorMessage:query')")
    @GetMapping("/{id}")
    public AjaxResult<InteriorMessageVo> getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(iInteriorMessageService.queryById(id));
    }

}
