package cn.oneplustow.sc.mapstruct;

import cn.oneplustow.common.mapstruct.IMapStruct;
import cn.oneplustow.sc.entity.SysOssConfig;
import cn.oneplustow.sc.entity.vo.SysOssConfigVo;
import org.mapstruct.Mapper;

/**
 * @author cc
 */
@Mapper(componentModel = "spring")
public interface SysOssConfigVoMapStruct extends IMapStruct<SysOssConfig, SysOssConfigVo> {

}
