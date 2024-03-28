package com.nowcoder.community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author 王修豪
 * @version 1.0
 * 发邮件的功能，相当于一个客户端
 */
@Component//是一个通用的bean
public class MailClient {
    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    @Autowired//直接注入就可以用
    private JavaMailSender mailSender;
    //收发是谁

    @Value("${spring.mail.username}")
    private String from;//发件人

    //封装一个共有的方法，不需要返回值，只要不报错就是发送成功，实现发邮件的逻辑
    public void sendMail(String to,String subject,String content){
        //发给谁，邮件的主题是什么，邮件的内容
        //构建JavaMailSender的MimeMessage
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);//true表示允许支持html文件
            mailSender.send(helper.getMimeMessage());
        } catch (Exception e) {
            logger.info("发送邮件失败："+e.getMessage());
        }
    }



}
