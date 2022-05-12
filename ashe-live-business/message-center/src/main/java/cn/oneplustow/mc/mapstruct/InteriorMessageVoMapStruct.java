package cn.oneplustow.mc.mapstruct;


import cn.oneplustow.mc.entity.InteriorMessage;
import cn.oneplustow.mc.entity.vo.InteriorMessageVo;
import cn.opl.mapstruct.IMapStruct;
import org.mapstruct.Mapper;

/**
 * @author cc
 */
@Mapper(componentModel = "spring")
public interface InteriorMessageVoMapStruct extends IMapStruct<InteriorMessageVo, InteriorMessage> {
}
