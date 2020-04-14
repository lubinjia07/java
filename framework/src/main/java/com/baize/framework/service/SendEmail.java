package com.baize.framework.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author lubinjia
 * @create 2020/4/14 23:11
 */
@Service
public class SendEmail {
    @Autowired
    private FreeMarkerConfigurer freemarker;
    @Autowired
    private JavaMailSenderImpl mailSender;

    /*public void sendMail() {

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
    }*/


    public void sendTxtMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人
        String receiver = "177@qq.com";
        simpleMailMessage.setTo(new String[]{receiver});
        simpleMailMessage.setFrom("176@163.com");
        simpleMailMessage.setSubject("Spring Boot Mail 邮件测试【文本】");
        simpleMailMessage.setText("这里是一段简单文本测试邮件。！！！！！请忽略！！！！！");
        // 发送邮件
        mailSender.send(simpleMailMessage);
//        logger.info("邮件已经发送到账户"+receiver);
        System.out.println("邮件已发送");
    }

    public void sendHtmlMail() {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            String receiver = "lubinjia@sina.com";//接收者账户
            String postCount = "540171689@qq.com";//发送者账户
            mimeMessageHelper.setTo(receiver);

            mimeMessageHelper.setFrom("540171689@qq.com");
            mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【HTML】");

            StringBuilder sb = new StringBuilder();
            sb.append("<html><head></head>");
            sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p></body>");
            sb.append("</html>");

            // 启用html
            mimeMessageHelper.setText(sb.toString(), true);
            // 发送邮件
            mailSender.send(mimeMessage);
//            logger.info("邮件已经从" + postCount + "->发送到账户" + receiver);
            System.out.println("邮件已发送");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送包含内嵌图片的邮件
     * @throws Exception
     */
    public void sendAttachedImageMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        String receiver="177@qq.com";//接收者账户
        String postCount="176@163.com";//发送者账户
        mimeMessageHelper.setTo(receiver);
        mimeMessageHelper.setFrom(postCount);
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【图片】");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p>");
        // cid为固定写法，imageId指定一个标识
        sb.append("<img src='https://www.google.com/images/branding/googlelogo/2x/googlelogo_light_color_272x92dp.png'></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);

        // 设置imageId
        FileSystemResource img = new FileSystemResource(new File("D:/壁纸/21.jpg"));
        mimeMessageHelper.addInline("imageId", img);

        // 发送邮件
        mailSender.send(mimeMessage);
//        logger.info("邮件已经从"+postCount+"->发送到账户"+receiver+",发送内容为："+img);
        System.out.println("邮件已发送");
    }

    /**
     * 发送包含附件的邮件
     * @throws Exception
     */
    public void sendAttendedFileMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        String receiver="177@qq.com";//接收者账户
        String postCount="176@163.com";//发送者账户
        mimeMessageHelper.setTo(receiver);
        mimeMessageHelper.setFrom(postCount);
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【附件】");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail annex test</p></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 设置附件
        FileSystemResource file = new FileSystemResource(new File("D:/简历/个人简历 朱旭.docx"));
        mimeMessageHelper.addAttachment("PersonalInformation.docx", file);

        // 发送邮件
        mailSender.send(mimeMessage);
//        logger.info("邮件已经从"+postCount+"->发送到账户"+receiver+",发送内容为："+file);
        System.out.println("邮件已发送");
    }

}
