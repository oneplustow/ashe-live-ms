package cn.oneplustow.live.mapstruct;

import cn.hutool.core.util.IdUtil;
import cn.oneplustow.common.constant.DbConstants;
import cn.oneplustow.common.mapstruct.IMapStruct;
import cn.oneplustow.live.entity.PlayRoom;
import cn.oneplustow.live.vo.SavePlayRoomDto;
import org.mapstruct.*;
import org.springframework.core.convert.converter.Converter;

/**
 * @author cc
 */
@Mapper(componentModel = "spring",imports = IdUtil.class)
public interface PlayRoom2SavePlayRoomDto extends IMapStruct<PlayRoom,SavePlayRoomDto> {

    /**
     * 转换
     * @inheritDoc
     * @param savePlayRoomDto
     * @return
     */
    @Mappings({
            @Mapping(target = "status",constant = DbConstants.PalyRoomStatus.NOT_START),
            @Mapping(target = "viewNumber",constant = "0L"),
    })
    @Override
    PlayRoom convert(SavePlayRoomDto savePlayRoomDto);
}
