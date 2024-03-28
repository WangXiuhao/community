package com.nowcoder.community;

import com.nowcoder.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.context.Context;


/**
 * @author 王修豪
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {
    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;//模板引擎

    @Test
    //发送邮件
    public void testMailClient(){
        mailClient.sendMail("wangxiuhao666@126.com","test","Welecome.");
    }

    @Test
    //发送html
    public void testHtmlEmail(){
        Context context = new Context();//传送参数
        context.setVariable("username","Leo");

        String content = templateEngine.process("/mail/demo", context);//生成动态网页 给他路径和数据
        System.out.println(content);

        mailClient.sendMail("wangxiuhao666@126.com","htmltest",content);
    }
}
