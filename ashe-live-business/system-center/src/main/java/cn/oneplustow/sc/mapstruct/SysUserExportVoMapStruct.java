package cn.oneplustow.sc.mapstruct;

import cn.oneplustow.common.mapstruct.IMapStruct;
import cn.oneplustow.sc.entity.SysUser;
import cn.oneplustow.sc.entity.vo.SysUserExportVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author cc
 */
@Mapper(componentModel = "spring")
public interface SysUserExportVoMapStruct extends IMapStruct<SysUserExportVo, SysUser> {
    @Mappings({
        @Mapping(target = "deptName",source = "dept.deptName"),
        @Mapping(target = "leader",source = "dept.leader")
    })
    @Override
    SysUserExportVo convert(SysUser sysUser);
}
