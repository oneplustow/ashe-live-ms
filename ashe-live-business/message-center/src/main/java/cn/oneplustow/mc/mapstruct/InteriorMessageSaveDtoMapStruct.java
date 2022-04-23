package cn.oneplustow.mc.mapstruct;


import cn.oneplustow.common.constant.Constants;
import cn.oneplustow.mc.entity.InteriorMessage;
import cn.oneplustow.mc.entity.dto.InteriorMessageSaveDto;
import cn.opl.mapstruct.IMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author cc
 */
@Mapper(componentModel = "spring")
public interface InteriorMessageSaveDtoMapStruct extends IMapStruct<InteriorMessageSaveDto, InteriorMessage> {

	@Mappings({
		@Mapping(target = "status",constant = Constants.InteriorMessageStatus.未读),
	})
	@Override
	InteriorMessage converFrom(InteriorMessageSaveDto saveDto);
}
