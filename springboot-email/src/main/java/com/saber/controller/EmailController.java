package com.saber.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RestController
public class EmailController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${mail.fromMail.sender}")
    private String sender;

    @Value("${mail.fromMail.receiver}")
    private String receiver;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送文本邮件
     *
     * @return
     */
    @RequestMapping("/sendMail")
    public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setSubject("Saber");
        message.setText("测试发送文本邮件！");
        try {
            javaMailSender.send(message);
            logger.info("邮件已发送");
        } catch (MailException e) {
            logger.error("邮件发送异常" + e.getMessage());
        }
        return "success";
    }

    @RequestMapping("/sendHtmlMail")
    public String sendHtmlMail() {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject("html mail");
            helper.setText(content, true);

            javaMailSender.send(message);
            logger.info("html邮件发送成功");
        } catch (MessagingException e) {
            logger.error("html邮件发送失败" + e.getMessage());
        }
        return "success";
    }

    /**
     * 发送带附件邮件
     *
     * @return
     */
    @RequestMapping("/sendFilesMail")
    public String sendFilesMail() {
        String filePath = "D:\\Users\\Saber\\Downloads\\说明.txt";
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject("file mail");
            helper.setText("这是一封带附件的邮件", true);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, fileSystemResource);

            javaMailSender.send(message);
            logger.error("带附件邮件发送成功");
        } catch (MessagingException e) {
            logger.error("带附件邮件发送失败" + e.getMessage());
        }
        return "success";
    }

    @RequestMapping("/sendInlineResourceMail")
    public String sendInlineResourceMail() {
        String id = "Saber01";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + id + "\' ></body></html>";
        String imgPath = "D:\\Download\\商城\\shop\\商城模板\\one\\images\\01.jpg";
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject("这是有图片的邮件");
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(imgPath));
            helper.addInline(id, file);

            javaMailSender.send(message);

            logger.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送嵌入静态资源的邮件时发生异常" + e.getMessage());
        }


        return "success";
    }
}
