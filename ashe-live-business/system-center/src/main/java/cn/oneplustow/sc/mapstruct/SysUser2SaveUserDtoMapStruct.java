package cn.oneplustow.sc.mapstruct;

import cn.oneplustow.api.sc.vo.SaveUserDto;
import cn.oneplustow.common.mapstruct.IMapStruct;
import cn.oneplustow.sc.entity.SysUser;
import org.mapstruct.Mapper;

/**
 * @author cc
 */
@Mapper(componentModel = "spring")
public interface SysUser2SaveUserDtoMapStruct extends IMapStruct<SysUser, SaveUserDto> {

}
