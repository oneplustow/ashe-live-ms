package cn.oneplustow.lc.mapstruct;

import cn.oneplustow.common.mapstruct.IMapStruct;
import cn.oneplustow.lc.vo.PlayRoomDetailVo;
import cn.oneplustow.lc.vo.PlayRoomPlayDetailVo;
import org.mapstruct.Mapper;

/**
 * @author cc
 */
@Mapper(componentModel = "spring")
public interface PlayRoomDetailVo2PlayRoomPlayDetailVo extends IMapStruct<PlayRoomDetailVo, PlayRoomPlayDetailVo> {

}
