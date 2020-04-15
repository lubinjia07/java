package com.baize.framework.service;

import com.baize.framework.domain.User;
import com.baize.framework.dto.email.FileMailDto;
import com.baize.framework.dto.email.ImageEmailDto;
import com.baize.framework.dto.email.MailDto;
import com.baize.framework.dto.email.TemplateEmailDto;
import com.baize.framework.util.FreeMarkerUtil;
import com.baize.framework.util.SendEmailUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lubinjia
 * @create 2020/4/16 0:20
 */
@Service
public class SendEmailDemoService {
    //发送人
    private static final String SENDER = "540171689@qq.com";
    //接收人
    private static final String RECEIVER = "lubinjia@sina.com";

    public void sendTxtMail() {
        MailDto mailDto = new MailDto();
        mailDto.setSubject("Spring Boot Mail 邮件测试【文本】");
        mailDto.setContent("这里是一段简单文本测试邮件。！！！！！请忽略！！！！！");
        mailDto.setSender(SENDER);
        mailDto.setReceiver(RECEIVER);
        SendEmailUtil.sendTxtMail(mailDto);
    }

    public void sendHtmlMail() {
        try {
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append("<html><head></head>");
            htmlContent.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p></body>");
            htmlContent.append("</html>");

            MailDto mailDto = new MailDto();
            mailDto.setSubject("Spring Boot Mail 邮件测试【HTML】");
            mailDto.setContent(htmlContent.toString());
            mailDto.setSender(SENDER);
            mailDto.setReceiver(RECEIVER);
            SendEmailUtil.sendHtmlMail(mailDto);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送包含内嵌图片的邮件
     *
     * @throws Exception
     */
    public void sendAttachedImageMail() {
        try {
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append("<html><head></head>");
            htmlContent.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p>");
            // cid为固定写法，imageId指定一个标识
            htmlContent.append("<img src='https://www.google.com/images/branding/googlelogo/2x/googlelogo_light_color_272x92dp.png'></body>");
            htmlContent.append("</html>");

            ImageEmailDto imageEmailDto = new ImageEmailDto();
            imageEmailDto.setSubject("Spring Boot Mail 邮件测试【图片】");
            imageEmailDto.setContent(htmlContent.toString());
            imageEmailDto.setSender(SENDER);
            imageEmailDto.setReceiver(RECEIVER);
            imageEmailDto.setImagePath("D:/壁纸/21.jpg");
            imageEmailDto.setImageId("imageId");
            SendEmailUtil.sendAttachedImageMail(imageEmailDto);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送包含附件的邮件
     *
     * @throws Exception
     */
    public void sendAttendedFileMail() {
        try {
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append("<html><head></head>");
            htmlContent.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail annex test</p></body>");
            htmlContent.append("</html>");

            FileMailDto fileMailDto = new FileMailDto();
            fileMailDto.setFilePath("D:/简历/个人简历 朱旭.docx");
            fileMailDto.setFileName("PersonalInformation.docx");
            fileMailDto.setSubject("Spring Boot Mail 邮件测试【附件】");
            fileMailDto.setContent(htmlContent.toString());
            fileMailDto.setSender(SENDER);
            fileMailDto.setReceiver(RECEIVER);
            SendEmailUtil.sendAttendedFileMail(fileMailDto);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据freemarker模板发送邮件
     */
    public void sendTemplateMail() {
        try {
            // 准备的 model data
            Map<String, Object> model = new HashMap<>();
            model.put("user", new User(1, "Helen"));

            TemplateEmailDto templateEmailDto = new TemplateEmailDto();
            templateEmailDto.setTemplateName("user.ftl");
            templateEmailDto.setModel(model);
            templateEmailDto.setSubject("Spring Boot Mail 邮件测试【HTML】");
            templateEmailDto.setSender(SENDER);
            templateEmailDto.setReceiver(RECEIVER);
            SendEmailUtil.sendTemplateMail(templateEmailDto);

        } catch (IOException | TemplateException | MessagingException e) {
            e.printStackTrace();
        }
    }

}
