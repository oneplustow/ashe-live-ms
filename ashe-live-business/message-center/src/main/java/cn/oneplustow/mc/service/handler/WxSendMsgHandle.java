package cn.oneplustow.mc.service.handler;


import cn.hutool.core.map.MapUtil;

import cn.oneplustow.api.sc.model.SimpleUser;
import cn.oneplustow.mc.entity.Message;
import cn.oneplustow.mc.entity.vo.SendMessageVo;
import cn.oneplustow.mc.wechat.WeChatBiz;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cc
 */
@Component
public class WxSendMsgHandle extends BaseSendMsgHandle {
	@Autowired
	private WeChatBiz weChatBiz;
//	@Autowired
//	private ISysConfigService configService;


	@Override
	public Message doSendMsg(String esReceiver, SendMessageVo sendMessageVo) {
		String esContent = sendMessageVo.getEsContent();
		String templateId ="";// configService.selectConfigByKey(esContent);
		Map<String, Object> esParam = sendMessageVo.getEsParam();
		List<WxMpTemplateData> wxMpTemplateDataList = new ArrayList<>();
		if(MapUtil.isNotEmpty(esParam)){
			esParam.forEach((k,v)->wxMpTemplateDataList.add(new WxMpTemplateData(k,v.toString())));
		}
		weChatBiz.sendTemplateMsg(templateId,esReceiver,wxMpTemplateDataList);
		return null;
	}

	@Override
	String getReceiver(SimpleUser user) {
		return user.getWeChatId();
	}


}
