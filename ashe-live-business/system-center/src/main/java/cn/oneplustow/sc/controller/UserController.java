package cn.oneplustow.sc.controller;

import cn.oneplustow.common.web.domain.AjaxResult;
import cn.oneplustow.sc.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cc
 * @date 14/09/2020 13:54
 */
@Api(tags = "用户中心")
@RestController
@RequestMapping("api/freshOrder/user")
public class UserController {

    @ApiOperation("获取用户信息")
    @GetMapping("/getUser")
    public AjaxResult getUser(){
        return AjaxResult.success(new UserEntity("1","cc","cc2020"));
    }
}
