package cn.oneplustow.lc.controller;

import cn.oneplustow.common.web.controller.BaseController;
import cn.oneplustow.lc.service.IOssrsService;
import cn.oneplustow.lc.vo.OssrsCallBackDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Ossrs流服务器的回调接口
 * @author CC
 * @title: OssrsCallBack
 * @projectName ashe-live-ms
 * @description: Ossrs流服务器的回调接口
 * @date 2021/4/1622:42
 */
@Api(tags = "Ossrs流服务器回调")
@RestController
@RequestMapping("/ossrs" )
public class OssrsController extends BaseController {

    @Autowired
    private IOssrsService ossrsService;

    /**
     * ossrs回调接口
     */
    @PostMapping("/public/callBack" )
    public int list(@RequestBody OssrsCallBackDto ossrsCallBackDto) {
        return ossrsService.callBack(ossrsCallBackDto);
    }
}
