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
public class SysUserListCriteria extends PageDomain {
    /** 部门ID */
    private Long deptId;

    /** 用户账号 */
    private String userName;

    /** 用户昵称 */
    private String nickName;

    /** 手机号码 */
    private String phonenumber;

    private String status;

    /** 创建时间开始 */
    private Date createTimeBegin;

    /** 创建时间结束 */
    private Date createTimeEnd;


}
