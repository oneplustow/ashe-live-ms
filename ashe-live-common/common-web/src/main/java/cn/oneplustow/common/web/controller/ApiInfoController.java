package cn.oneplustow.common.web.controller;


import cn.oneplustow.common.domain.AjaxResult;
import cn.oneplustow.common.web.util.ApiInfoContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统api信息Controller
 *
 * @author cc
 * @date 2021-06-18
 */
@RestController
@RequestMapping("/system/info" )
public class ApiInfoController extends BaseController {

    @Autowired
    private ApiInfoContext apiInfoContext;

    /**
     * 初始化系统API信息
     */
    @GetMapping("/init" )
    public AjaxResult init() {
        return AjaxResult.success(apiInfoContext.init());
    }
}
