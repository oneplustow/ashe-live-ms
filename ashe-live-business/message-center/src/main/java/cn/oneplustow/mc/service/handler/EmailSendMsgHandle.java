package cn.oneplustow.mc.service.handler;


import cn.oneplustow.api.sc.model.SimpleUser;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.oneplustow.mc.entity.Message;
import cn.oneplustow.mc.vo.SendMessageVo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.StringWriter;

/**
 * @author cc
 */
@Slf4j
@Component
public class EmailSendMsgHandle extends BaseSendMsgHandle {

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration configuration ;


    @Override
    Message doSendMsg(String esReceiver, SendMessageVo sendMessageVo) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            // 设置发送方邮箱地址
            helper.setFrom(emailFrom);
            helper.setTo(esReceiver);
            helper.setSubject(sendMessageVo.getEsTitle());
            helper.setText(getTemplateContent(sendMessageVo), true);
            mailSender.send(message);
        } catch (Exception e) {
            throw new WarningMessageException("发送邮件失败，请稍后重试",e);
        }
        return null;
    }

    private String getTemplateContent(SendMessageVo sendMessageVo) {
        try {
            Template template = configuration.getTemplate(sendMessageVo.getEsContent());
            StringWriter writer = new StringWriter();
            template.process(sendMessageVo.getEsParam(), writer);
            return writer.toString();
        } catch (Exception e) {
            throw new WarningMessageException("合成模板内容失败",e);
        }
    }

    @Override
    String getReceiver(SimpleUser user) {
        return user.getEmail();
    }

}
