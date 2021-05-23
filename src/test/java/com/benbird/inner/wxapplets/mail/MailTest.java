package com.benbird.inner.wxapplets.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;

import javax.mail.internet.MimeMessage;

/**
 * 项目名: wx-applets
 * 创建者: Admin
 * 创建时间: 2021/5/19
 * 描述:
 *
 * @author Admin
 */

@SpringBootTest
public class MailTest {

    @Autowired
    private JavaMailSender javaMailSender;


    @Test
    public void testSimple(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("18326676004@163.com");
        simpleMailMessage.setTo("1370605410@qq.com");
        simpleMailMessage.setSubject("test");
        simpleMailMessage.setText("这是测试内容");

        javaMailSender.send(simpleMailMessage);
    }


    @Test
    public void test(){

    }
}
