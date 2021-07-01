package cn.oneplustow.uc.mapstruct;

import cn.oneplustow.api.sc.vo.SaveUserDto;
import cn.oneplustow.common.mapstruct.IMapStruct;
import cn.oneplustow.uc.vo.SaveMemberDto;
import org.mapstruct.Mapper;

/**
 * @author cc
 */
@Mapper(componentModel = "spring")
public interface SaveMemberDto2SaveUserDto extends IMapStruct<SaveMemberDto, SaveUserDto> {
}
