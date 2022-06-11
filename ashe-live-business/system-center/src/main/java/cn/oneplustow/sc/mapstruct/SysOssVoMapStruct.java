package cn.oneplustow.sc.mapstruct;

import cn.oneplustow.common.mapstruct.IMapStruct;
import cn.oneplustow.sc.entity.SysOss;
import cn.oneplustow.sc.entity.vo.SysOssVo;
import org.mapstruct.Mapper;

/**
 * @author cc
 */
@Mapper(componentModel = "spring")
public interface SysOssVoMapStruct extends IMapStruct<SysOss, SysOssVo> {

}
