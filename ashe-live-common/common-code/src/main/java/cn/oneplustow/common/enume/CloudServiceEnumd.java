package cn.oneplustow.common.enume;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 云存储服务商枚举
 *
 */
@Getter
@AllArgsConstructor
public enum CloudServiceEnumd {

	/**
	 * 七牛云
	 */
	QINIU("qiniu", null),

	/**
	 * 阿里云
	 */
	ALIYUN("aliyun", null),

	/**
	 * 腾讯云
	 */
	QCLOUD("qcloud", null),

	/**
	 * minio
	 */
	MINIO("minio", null),

	LOCAL("local", null);

	private final String value;

	private final Class<?> serviceClass;

	public static Class<?> getServiceClass(String value) {
		for (CloudServiceEnumd clazz : values()) {
			if (clazz.getValue().equals(value)) {
				return clazz.getServiceClass();
			}
		}
		return null;
	}

	public static String getServiceName(String value) {
		for (CloudServiceEnumd clazz : values()) {
			if (clazz.getValue().equals(value)) {
				return StringUtils.uncapitalize(clazz.getServiceClass().getSimpleName());
			}
		}
		return null;
	}


}
