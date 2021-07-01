package cn.oneplustow.lc.mapstruct;

import cn.hutool.core.util.IdUtil;
import cn.oneplustow.common.mapstruct.IMapStruct;
import cn.oneplustow.lc.entity.PlayRoom;
import cn.oneplustow.lc.vo.PlayRoomPageVo;
import org.mapstruct.Mapper;

/**
 * @author cc
 */
@Mapper(componentModel = "spring",imports = IdUtil.class)
public interface PlayRoom2PlayRoomPageVo extends IMapStruct<PlayRoom, PlayRoomPageVo> {

}
