package com.baize.framework.util;

import com.baize.framework.dto.email.FileMailDto;
import com.baize.framework.dto.email.ImageEmailDto;
import com.baize.framework.dto.email.MailDto;
import com.baize.framework.dto.email.TemplateEmailDto;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

/**
 * @author lubinjia
 * @create 2020/4/14 23:11
 */
@Service
public class SendEmailUtil {
    @Autowired
    private JavaMailSenderImpl mailSender;

    private static SendEmailUtil SEND_EMAIL_UTIL;

    @Autowired
    private FreeMarkerUtil freeMarkerUtil;

    @PostConstruct
    public void init(){
        SEND_EMAIL_UTIL = this;
    }

    public static void sendTxtMail(MailDto mailDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailDto.getSender());
        simpleMailMessage.setTo(new String[]{mailDto.getReceiver()});
        simpleMailMessage.setSubject(mailDto.getSubject());
        simpleMailMessage.setText(mailDto.getContent());
        // 发送邮件
        SEND_EMAIL_UTIL.mailSender.send(simpleMailMessage);
//        logger.info("邮件已经发送到账户"+receiver);
        System.out.println("邮件已发送");
    }

    public static void sendHtmlMail(MailDto mailDto) throws MessagingException {
        MimeMessage mimeMessage = SEND_EMAIL_UTIL.mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom(mailDto.getSender());
        mimeMessageHelper.setTo(mailDto.getReceiver());
        mimeMessageHelper.setSubject(mailDto.getSubject());

        // 启用html
        mimeMessageHelper.setText(mailDto.getContent(), true);
        // 发送邮件
        SEND_EMAIL_UTIL.mailSender.send(mimeMessage);
//            logger.info("邮件已经从" + postCount + "->发送到账户" + receiver);
        System.out.println("邮件已发送");
    }

    /**
     * 发送包含内嵌图片的邮件
     *
     * @throws Exception
     */
    public static void sendAttachedImageMail(ImageEmailDto imageEmailDto) throws MessagingException {
        MimeMessage mimeMessage = SEND_EMAIL_UTIL.mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(imageEmailDto.getSender());
        mimeMessageHelper.setTo(imageEmailDto.getReceiver());
        mimeMessageHelper.setSubject(imageEmailDto.getSubject());

        // 启用html
        mimeMessageHelper.setText(imageEmailDto.getContent(), true);

        // 设置imageId
        FileSystemResource img = new FileSystemResource(new File(imageEmailDto.getImagePath()));
        mimeMessageHelper.addInline(imageEmailDto.getImageId(), img);

        // 发送邮件
        SEND_EMAIL_UTIL.mailSender.send(mimeMessage);
//        logger.info("邮件已经从"+postCount+"->发送到账户"+receiver+",发送内容为："+img);
        System.out.println("邮件已发送");
    }

    /**
     * 发送包含附件的邮件
     *
     * @throws Exception
     */
    public static void sendAttendedFileMail(FileMailDto fileMailDto) throws MessagingException {
        MimeMessage mimeMessage = SEND_EMAIL_UTIL.mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        mimeMessageHelper.setFrom(fileMailDto.getSender());
        mimeMessageHelper.setTo(fileMailDto.getReceiver());
        mimeMessageHelper.setSubject(fileMailDto.getSubject());

        // 启用html
        mimeMessageHelper.setText(fileMailDto.getContent(), true);
        // 设置附件
        FileSystemResource file = new FileSystemResource(new File(fileMailDto.getFilePath()));
        mimeMessageHelper.addAttachment(fileMailDto.getFileName(), file);

        // 发送邮件
        SEND_EMAIL_UTIL.mailSender.send(mimeMessage);
//        logger.info("邮件已经从"+postCount+"->发送到账户"+receiver+",发送内容为："+file);
        System.out.println("邮件已发送");
    }

    /**
     * 根据freemarker模板发送邮件
     */
    public static void sendTemplateMail(TemplateEmailDto templateEmailDto) throws MessagingException, IOException, TemplateException {
        // 创建 MimeMessageHelper 对象
        MimeMessage mimeMessage = SEND_EMAIL_UTIL.mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        // 设置发件人地址
        mimeMessageHelper.setFrom(new InternetAddress(templateEmailDto.getSender()));
        // 设置收件人地址
        mimeMessageHelper.setTo(new InternetAddress(templateEmailDto.getReceiver()));
        mimeMessageHelper.setSubject(templateEmailDto.getSubject());
        // 获取模版对象
        String content = SEND_EMAIL_UTIL.freeMarkerUtil.getTemplateContent(templateEmailDto.getTemplateName(), templateEmailDto.getModel());
        mimeMessageHelper.setText(content, true);
        // 发送邮件
        SEND_EMAIL_UTIL.mailSender.send(mimeMessage);
    }

}

    /*
    public void sendMail() {
        // 发件人地址
        String from = "zhaoxiaoyu@yundaex.com";
        // 收件人地址
        String to = "787029866@qq.com";

        // 设定系统属性
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", ConfigMessage.getProperty("mail.smtp.host"));
        properties.put("mail.smtp.port", ConfigMessage.getProperty("mail.smtp.port"));
        properties.put("mail.smtp.auth", ConfigMessage.getProperty("mail.smtp.auth"));

        // 获取网络连接 授权
        Mail_authenticator auth = new Mail_authenticator("zhaoxiaoyu@yundaex.com", "Yd123456");
        // 获取 mail session 对象
        Session session = Session.getInstance(properties, auth);
        // 创建 消息 对象
        MimeMessage message = new MimeMessage(session);

        try {
            // 创建 MimeMessageHelper 对象
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "utf-8");
            // 设置发件人地址
            mimeMessageHelper.setFrom(new InternetAddress(from));
            // 设置收件人地址
            mimeMessageHelper.setTo(new InternetAddress(to));

            // 设置主题
            mimeMessageHelper.setSubject("Hello world");

            // message.setText("This is my first sending email by java mail.");

            // 获取模版对象
            Template template = freemarker.getConfiguration().getTemplate("user.ftl");

            // 准备的 model data
            Map<String, User> map = new HashMap<String, User>();
            map.put("user", new User("Hellen", 20));

            // 解析 模版和 数据
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);

            // file 本地路径下的文件
            File file = new File("G:/chrome download/学生.xls");
            // 添加附件
            mimeMessageHelper.addAttachment("学生.xls", file);

            // 添加内嵌图片
            mimeMessageHelper.addInline("picture", new File("E:/图片/美图图库/baby.jpg"));

            // 设置 消息内容
            mimeMessageHelper.setText(content, true);

            // 发送消息
            Transport.send(message);
            System.out.println("send email successfully...");

        } catch (AddressException e) {
            e.printStackTrace();
//            logger.error(e.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
//            logger.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
//            logger.error(e.getMessage());
        } catch (TemplateException e) {
            e.printStackTrace();
//            logger.error(e.getMessage());
        }
    }
    */
