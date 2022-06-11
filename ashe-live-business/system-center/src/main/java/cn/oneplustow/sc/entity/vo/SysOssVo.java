package cn.oneplustow.sc.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * OSS云存储视图对象 sys_oss
 *
 */
@Data
@ApiModel("OSS云存储视图对象")
public class SysOssVo {

	private static final long serialVersionUID = 1L;

	/**
	 *  云存储主键
	 */
	@ApiModelProperty("云存储主键")
	private Long ossId;

	/**
	 * 原名
	 */
	@ApiModelProperty("原名")
	private String originalName;

	/**
	 * 文件后缀名
	 */
	@ApiModelProperty("文件后缀名")
	private String fileSuffix;

	/**
	 * URL地址
	 */
	@ApiModelProperty("URL地址")
	private String url;

	@ApiModelProperty("下载次数")
	private Integer downloadCount;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private Date createTime;



}
