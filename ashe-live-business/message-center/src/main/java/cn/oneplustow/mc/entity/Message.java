package cn.oneplustow.mc.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 消息
 * @Author: cc
 * @Date:  2019-04-09
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mc_message")
public class Message {

	/**推送内容*/
	private String esContent;

	/**推送所需参数Json格式*/
	private String esParam;

	/**接收人*/
	private String esReceiver;

	/**推送失败原因*/
	private String esResult;

	/**发送次数*/
	private Integer esSendNum;

	/**推送状态 0未推送 1推送成功 2推送失败*/
	private String esSendStatus;

	private java.util.Date esSendTime;

	/**消息标题*/
	private String esTitle;

	/**推送方式：1短信 2邮件 3微信*/
	private String esType;

	/**备注*/
	private String remark;
}
