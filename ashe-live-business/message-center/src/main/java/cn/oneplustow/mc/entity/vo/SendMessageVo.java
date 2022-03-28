package cn.oneplustow.mc.entity.vo;


import cn.oneplustow.common.enume.SendMsgType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description: 消息
 * @Author: cc
 * @Date:  2019-04-09
 * @Version: V1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Accessors(chain = true)
public class SendMessageVo {
	/**接收人*/
	private Collection<Long> esUserId;

	/**接收人*/
	private List<String> esReceiver;

	/**消息标题*/
	private String esTitle;

	/**推送内容*/
	private String esContent;

	/**推送所需参数Json格式*/
	private Map<String,Object> esParam;

	/**推送方式：1短信 2邮件 3微信*/
	private SendMsgType esType;

	private Boolean async;
}
