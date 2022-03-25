package cn.oneplustow.mc.service.handler;


import cn.oneplustow.api.sc.model.SimpleUser;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.mc.entity.Message;
import cn.oneplustow.mc.vo.SendMessageVo;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.stereotype.Component;

/**
 * @author cc
 * 短信发送处理器
 */
@Component
public class SmsSendMsgHandle extends BaseSendMsgHandle{

//	@Autowired
//	private ISysConfigService configService;

	@Override
	Message doSendMsg(String esReceiver, SendMessageVo sendMessageVo) {
		SmsConfig config = getConfig();
		try {
			Client client = new Client(config);
			SendSmsRequest sendSmsRequest = new SendSmsRequest()
				.setPhoneNumbers(esReceiver)
				.setSignName(config.getSignatureName())
				.setTemplateCode(sendMessageVo.getEsContent())
				.setTemplateParam(JSONObject.toJSONString(sendMessageVo.getEsParam()));
			SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
			SendSmsResponseBody body = sendSmsResponse.getBody();
			String code = body.getCode();
		} catch (Exception e) {
			throw new WarningMessageException("短信发送错误",e);
		}
		return null;
	}

	@Override
	String getReceiver(SimpleUser user) {
		return user.getPhonenumber();
	}

	private SmsConfig getConfig(){
		String configJson =""; //configService.selectConfigByKey(Constants.SysConfigKey.短信配置);
		return JSONObject.parseObject(configJson, SmsConfig.class);
	}

	public static class SmsConfig extends Config{
		/**签名名称*/
		private String signatureName;

		public String getSignatureName() {
			return signatureName;
		}

		public void setSignatureName(String signatureName) {
			this.signatureName = signatureName;
		}
	}


}
