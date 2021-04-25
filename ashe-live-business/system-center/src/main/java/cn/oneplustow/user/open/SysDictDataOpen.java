package cn.oneplustow.user.open;


import cn.hutool.core.util.ObjectUtil;
import cn.oneplustow.common.web.controller.BaseController;
import cn.oneplustow.user.entity.SysDictData;
import cn.oneplustow.user.entity.SysUser;
import cn.oneplustow.user.service.ISysDictDataService;
import cn.oneplustow.user.service.ISysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Api(tags = "用户信息控制器")
@RestController
@RequestMapping("/fegin/system/dictData")
public class SysDictDataOpen extends BaseController
{
    @Autowired
    private ISysDictDataService dictDataService;

    /**
     * 根据用户编号获取详细信息
     */
    @GetMapping("selectDictDataByType")
    public List<SysDictData> selectDictDataByType(@RequestParam("dictType") String dictType)
    {
        if (ObjectUtil.isNotNull(dictType))
        {
            return dictDataService.selectDictDataByType(dictType);
        }
        return new ArrayList<>();
    }

}