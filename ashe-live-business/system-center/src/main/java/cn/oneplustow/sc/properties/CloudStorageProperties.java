package cn.oneplustow.sc.properties;

import lombok.Data;

import java.util.Date;

/**
 * OSS云存储 配置属性
 *
 */
@Data
public class CloudStorageProperties {

	/**
	 * 访问站点
	 */
	private String endpoint;

	/**
	 * 前缀
	 */
	private String prefix;

	/**
	 * ACCESS_KEY
	 */
	private String accessKey;

	/**
	 * SECRET_KEY
	 */
	private String secretKey;

	/**
	 * 桶名称（映射点）
	 */
	private String bucketName;

	/**
	 * 域（实际地址）
	 */
	private String region;

	/**
	 * 是否https（Y=是,N=否）
	 */
	private String isHttps;

	/**
	 * 更新时间
	 */
	private Date updateTime;

}
