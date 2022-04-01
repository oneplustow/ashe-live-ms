package cn.oneplustow.ac.mapstruct;

import cn.oneplustow.ac.entity.LoginUserDetails;
import cn.oneplustow.api.ac.model.LoginUser;
import cn.oneplustow.api.sc.model.DictDataResp;
import cn.oneplustow.common.mapstruct.IMapStruct;
import org.mapstruct.Mapper;

/**
 * @author cc
 */
@Mapper(componentModel = "spring")
public interface LoginUser2LoginUserDetailMapStruct extends IMapStruct<LoginUser, LoginUserDetails> {

}
