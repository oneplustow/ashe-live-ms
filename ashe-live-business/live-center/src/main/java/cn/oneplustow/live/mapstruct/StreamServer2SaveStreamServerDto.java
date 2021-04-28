package cn.oneplustow.live.mapstruct;

import cn.hutool.core.util.IdUtil;
import cn.oneplustow.common.constant.DbConstants;
import cn.oneplustow.common.mapstruct.IMapStruct;
import cn.oneplustow.live.entity.PlayRoom;
import cn.oneplustow.live.entity.StreamServer;
import cn.oneplustow.live.vo.SavePlayRoomDto;
import cn.oneplustow.live.vo.SaveStreamServerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author cc
 */
@Mapper(componentModel = "spring",imports = IdUtil.class)
public interface StreamServer2SaveStreamServerDto extends IMapStruct<StreamServer, SaveStreamServerDto> {

    /**
     * 转换
     * @inheritDoc
     * @param streamServerDto
     * @return
     */
    @Mappings({
            @Mapping(target = "status",constant = DbConstants.SteamServerStatus.WAIT_CONNECTION)
    })
    @Override
    StreamServer convert(SaveStreamServerDto streamServerDto);
}
