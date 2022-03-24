package cn.oneplustow.mc.service.handler;

import cn.oneplustow.api.sc.model.SimpleUser;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.mc.entity.Message;
import cn.oneplustow.mc.vo.SendMessageVo;
import com.alibaba.fastjson.JSONObject;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.Map;

/**
 * @author cc
 * 短信发送处理器
 */
@Slf4j
@Component
public class ImSendMsgHandle extends BaseSendMsgHandle{

//	@Autowired
//	private IInteriorMessageService interiorMessageService;
//
//	@Autowired
//	private ISysConfigService configService;

	private Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
	{
		StringTemplateLoader templateLoader = new StringTemplateLoader();
		configuration.setTemplateLoader(templateLoader);
		configuration.setDefaultEncoding("UTF-8");
	}

	@Override
	Message doSendMsg(String esReceiver, SendMessageVo sendMessageVo) {
		Map<String, Object> esParam = sendMessageVo.getEsParam();
		ImTemplate config = getConfig(sendMessageVo.getEsContent());
		String title = processTemplate(config.getTitle(), esParam);
		String content = processTemplate(config.getContent(), esParam);
//		InteriorMessageSaveDto messageSaveDto = InteriorMessageSaveDto.builder()
//			.title(title)
//			.content(content)
//			.toUser(Long.valueOf(esReceiver))
//			.build();
//		interiorMessageService.saveMessage(messageSaveDto);
		return null;
	}

	String processTemplate(String content, Map<String,Object> param){
		StringWriter stringWriter = new StringWriter();
		try {
			Template template = new Template("", content, configuration);
			template.process(param, stringWriter);
			return stringWriter.toString();
		}catch (Exception e){
			throw new WarningMessageException("模板合成错误",e);
		}
	}


	@Override
	String getReceiver(SimpleUser user) {
		return user.getUserId() + "";
	}

	private ImTemplate getConfig(String configKey){
		String configJson = "";//configService.selectConfigByKey(configKey);
		return JSONObject.parseObject(configJson, ImTemplate.class);
	}

	@Data
	public static class ImTemplate {
		/**标题*/
		private String title;

		/**内容*/
		private String content;

	}

}
