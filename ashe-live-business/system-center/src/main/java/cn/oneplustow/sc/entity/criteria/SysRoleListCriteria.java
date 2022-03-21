package cn.oneplustow.sc.entity.criteria;


import cn.oneplustow.common.web.page.PageDomain;
import lombok.Data;

import java.util.Date;

/**
 * @author CC
 * @title: SysUserListCriteria
 * @projectName ashe-live-ms
 * @description:
 * @date 2022/3/2015:09
 */
@Data
public class SysRoleListCriteria extends PageDomain {

    /** 角色名称 */
    private String roleName;

    /** 角色权限 */
    private String roleKey;

    /** 角色状态（0正常 1停用） */
    private String status;

    /** 创建时间开始 */
    private Date createTimeBegin;

    /** 创建时间结束 */
    private Date createTimeEnd;


}
