package cn.oneplustow.live.mapstruct;

import cn.hutool.core.util.IdUtil;
import cn.oneplustow.common.constant.DbConstants;
import cn.oneplustow.common.mapstruct.IMapStruct;
import cn.oneplustow.live.entity.PlayRoom;
import cn.oneplustow.live.vo.PlayRoomPageVo;
import cn.oneplustow.live.vo.SavePlayRoomDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author cc
 */
@Mapper(componentModel = "spring",imports = IdUtil.class)
public interface PlayRoom2PlayRoomPageVo extends IMapStruct<PlayRoom, PlayRoomPageVo> {

}
