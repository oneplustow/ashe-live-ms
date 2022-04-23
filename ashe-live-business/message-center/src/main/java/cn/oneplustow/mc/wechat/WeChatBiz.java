package cn.oneplustow.mc.wechat;

import cn.hutool.core.util.StrUtil;
import cn.oneplustow.api.ac.util.SecurityUtils;
import cn.oneplustow.common.exception.WarningMessageException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpQrcodeService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author CC
 * @title: WeChatBiz
 * @projectName railway-journal
 * @description:
 * @date 2021/8/1910:37
 */
@Component
public class WeChatBiz {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WxMpConfigStorage wxMpConfigStorage;
//    @Autowired
//    private WxMpMessageRouter wxMpMessageRouter;

    private static final String ENCRYPT_TYPE_RAW = "raw";
    private static final String ENCRYPT_TYPE_AES = "aes";

    public void sendTemplateMsg(String templateId,String openId,List<WxMpTemplateData> wxMpTemplateDatas){
        WxMpTemplateMessage wxMpTemplateMessage = WxMpTemplateMessage.builder()
            .templateId(templateId)
            .toUser(openId)
            .data(wxMpTemplateDatas).build();
        try {
            String msgId = wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        }catch (Exception e){
            throw new WarningMessageException("发送微信模板详细错误",e);
        }
    }

//    public String getBindingQrCode(Long userId){
//        userId = userId != null ? userId : SecurityUtils.getUserId();
//        String sceneStr = Constants.WeChatQrOperation.绑定用户 + ":" + userId;
//        try {
//            WxMpQrcodeService qrcodeService = wxMpService.getQrcodeService();
//            //设置三分钟过期时间
//            WxMpQrCodeTicket wxMpQrCodeTicket = qrcodeService.qrCodeCreateTmpTicket(sceneStr, 60 * 300);
//            return qrcodeService.qrCodePictureUrl(wxMpQrCodeTicket.getTicket());
//        }catch (Exception e){
//            throw new WarningMessageException("获取微信二维码错误",e);
//        }
//    }


//    public String event(WeChatRequestVol weChatRequestVo, HttpServletRequest request){
//        String timestamp = weChatRequestVo.getTimestamp();
//        String nonce = weChatRequestVo.getNonce();
//        if (!wxMpService.checkSignature(timestamp,
//            nonce, weChatRequestVo.getSignature())) {
//            // 消息签名不正确，说明不是公众平台发过来的消息
//            throw new WarningMessageException("非法请求");
//        }
//
//        String echostr = weChatRequestVo.getEchostr();
//        if (StrUtil.isNotBlank(echostr)) {
//            // 说明是一个仅仅用来验证的请求，回显echostr
//            return echostr;
//        }
//
//        String encryptType = weChatRequestVo.getEncrypt_type();
//        if (StrUtil.isBlank(encryptType)) {
//            encryptType = ENCRYPT_TYPE_RAW;
//        }
//        WxMpXmlOutMessage outMessage = null;
//        try {
//            if (ENCRYPT_TYPE_RAW.equals(encryptType)) {
//                // 明文传输的消息
//                WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
//                outMessage = wxMpMessageRouter.route(inMessage);
//            }
//            if (ENCRYPT_TYPE_AES.equals(encryptType)) {
//                // 是aes加密的消息
//                String msgSignature = weChatRequestVo.getMsg_signature();
//                WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(
//                    request.getInputStream(), wxMpConfigStorage, timestamp, nonce, msgSignature);
//                outMessage = wxMpMessageRouter.route(inMessage);
//            }
//        }catch (IOException e){
//            throw new WarningMessageException("获取消息内容失败");
//        }
//        return outMessage == null ? "" : outMessage.toEncryptedXml(wxMpConfigStorage);
//    }

}
