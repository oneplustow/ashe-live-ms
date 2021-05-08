package cn.oneplustow.uc.mapstruct;

import cn.oneplustow.common.mapstruct.IMapStruct;
import cn.oneplustow.uc.entity.Member;
import cn.oneplustow.uc.vo.SaveMemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * @author cc
 */
@Mapper(componentModel = "spring")
public interface SaveMemberDto2Member extends IMapStruct<SaveMemberDto, Member> {

    /**
     * 转换
     * @param saveMemberDto
     * @return
     */
    @Mappings({
            //@Mapping(target = "account",source = "userName")
    })
    @Override
    Member convers(SaveMemberDto saveMemberDto);
}
