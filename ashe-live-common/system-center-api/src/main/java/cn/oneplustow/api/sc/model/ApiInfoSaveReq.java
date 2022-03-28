package cn.oneplustow.api.sc.model;


import lombok.Data;

/**
 * 系统api信息对象 sys_api_info
 * 
 * @author cc
 * @date 2021-06-18
 */
@Data
public class ApiInfoSaveReq
{
    private static final long serialVersionUID = 1L;

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


}
