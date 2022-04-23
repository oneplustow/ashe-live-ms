package cn.oneplustow.sc.fegin;


import cn.hutool.core.util.ObjectUtil;
import cn.oneplustow.api.sc.model.DictDataResp;
import cn.oneplustow.api.sc.service.DictDataFeginApi;
import cn.oneplustow.sc.entity.SysDictData;
import cn.oneplustow.sc.service.ISysDictDataService;
import cn.opl.mapstruct.MapStructContext;
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
public class DictDataFeginApiImpl implements DictDataFeginApi {
    @Autowired
    private ISysDictDataService dictDataService;
    @Autowired
    private MapStructContext mapStructContext;

    /**
     * 根据用户编号获取详细信息
     */
    @Override
    @GetMapping("selectDictDataByType")
    public List<DictDataResp> selectDictDataByType(@RequestParam("dictType") String dictType) {
        return mapStructContext.conver(
            dictDataService.selectDictDataByType(dictType),DictDataResp.class);
    }

}