package cn.oneplustow.sc.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 系统api信息对象 sys_api_info
 * 
 * @author cc
 * @date 2021-06-18
 */
@Data
public class SysApiInfo
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @TableId(type = IdType.AUTO)
    private Long id;

    private String type;

    /** 接口名称 */
    private String name;

    /** 描述 */
    private String describe;

    /** 接口地址 */
    private String url;

    /** 系统标志 */
    private String systemFlag;

    /** 权限 */
    private String permission;

    /** 创建时间 */
    private Date createTime;

}
