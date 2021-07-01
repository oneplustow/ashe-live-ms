package cn.oneplustow.lc.mapstruct;

import cn.hutool.core.util.IdUtil;
import cn.oneplustow.common.mapstruct.IMapStruct;
import cn.oneplustow.lc.entity.PlayRoom;
import cn.oneplustow.lc.entity.StreamServerAllotRecord;
import cn.oneplustow.lc.vo.PlayRoomDetailVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author cc
 */
@Mapper(componentModel = "spring",imports = IdUtil.class)
public interface PlayRoom2PlayRoomDetailVo extends IMapStruct<PlayRoom, PlayRoomDetailVo> {


    /**
     * 对象转换
     * @param playRoom
     * @param allotRecord
     * @return
     */
    @Mappings({
            @Mapping(source = "playRoom.id",target = "id"),
            @Mapping(source = "playRoom.status",target = "status")
    })
    PlayRoomDetailVo convers(PlayRoom playRoom, StreamServerAllotRecord allotRecord);
}
