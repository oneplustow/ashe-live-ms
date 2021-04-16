package cn.oneplustow.live.controller;

import cn.oneplustow.common.web.controller.BaseController;
import cn.oneplustow.common.web.domain.AjaxResult;
import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.live.entity.PlayRecord;
import cn.oneplustow.live.service.IOssrsService;
import cn.oneplustow.live.vo.OssrsCallBackVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Ossrs流服务器的回调接口
 * @author CC
 * @title: OssrsCallBack
 * @projectName ashe-live-ms
 * @description: Ossrs流服务器的回调接口
 * @date 2021/4/1622:42
 */
@RestController
@RequestMapping("/ossrs" )
public class OssrsController extends BaseController {

    @Autowired
    private IOssrsService ossrsService;

    /**
     * ossrs回调接口
     */
    @GetMapping("/callBack" )
    public int list(OssrsCallBackVo ossrsCallBackVo) {
        return ossrsService.callBack(ossrsCallBackVo);
    }
}
