package cn.oneplustow.sc.service;


import cn.oneplustow.sc.entity.SysOss;
import cn.oneplustow.sc.entity.vo.UploadResult;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 云存储策略
 * @author cc
 */
public interface ICloudStorageStrategy {

	void createBucket();

	default boolean isExist(SysOss sysOss){return true;}

	long download(SysOss sysOss, OutputStream outputStream);

	/**
	 * 获取服务商类型
	 */
	String getServiceType();

	/**
	 * 文件路径
	 *
	 * @param prefix 前缀
	 * @param suffix 后缀
	 * @return 返回上传路径
	 */
	String getPath(String prefix, String suffix);

	/**
	 * 文件上传
	 *
	 * @param data 文件字节数组
	 * @param path 文件路径，包含文件名
	 * @return 返回http地址
	 */
	UploadResult upload(byte[] data, String path, String contentType);

	/**
	 * 文件删除
	 *
	 * @param path 文件路径，包含文件名
	 */
	void delete(SysOss sysOss);

	/**
	 * 文件上传
	 *
	 * @param data   文件字节数组
	 * @param suffix 后缀
	 * @return 返回http地址
	 */
	UploadResult uploadSuffix(byte[] data, String suffix, String contentType);

	/**
	 * 文件上传
	 *
	 * @param inputStream 字节流
	 * @param path        文件路径，包含文件名
	 * @return 返回http地址
	 */
	UploadResult upload(InputStream inputStream, String path, String contentType);

	/**
	 * 文件上传
	 *
	 * @param inputStream 字节流
	 * @param suffix      后缀
	 * @return 返回http地址
	 */
	UploadResult uploadSuffix(InputStream inputStream, String suffix, String contentType);

	String getEndpointLink();
}
