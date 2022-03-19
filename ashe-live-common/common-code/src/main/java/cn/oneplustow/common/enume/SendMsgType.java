package cn.oneplustow.common.enume;

import cn.hutool.core.util.StrUtil;

/**
 * 发送消息类型枚举
 * @author cc
 */
public enum SendMsgType {

	/**短信*/
	SMS("1", "smsSendMsgHandle"),

	/**邮件*/
	EMAIL("2", "emailSendMsgHandle"),

	/**微信*/
	WX("3","wxSendMsgHandle"),

	/**站内信*/
	IM("4","imSendMsgHandle");

	private String type;

	private String implClass;

	private SendMsgType(String type, String implClass) {
		this.type = type;
		this.implClass = implClass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImplClass() {
		return implClass;
	}

	public void setImplClass(String implClass) {
		this.implClass = implClass;
	}

	public static SendMsgType getByType(String type) {
		if (StrUtil.isBlank(type)) {
			return null;
		}
		for (SendMsgType val : values()) {
			if (val.getType().equals(type)) {
				return val;
			}
		}
		return null;
	}
}
