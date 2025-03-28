package com.novelnet.demo.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

/**
 * 邮箱认证工具
 */
public class SendMail {
    //邮件服务器主机
    //传输协议SMTP(Simple Mail Transfer Protocol)
    public static String mailboxVerification(String email) throws MessagingException {
        //创建一个Properties对象
        Properties properties = new Properties();
        //设置主机
        properties.setProperty("mail.host", "smtp.qq.com");
        //设置传输协议
        properties.setProperty("mail.transport.protocol", "smtp");
        //设置允许邮箱授权认证
        properties.setProperty("mail.smtp.auth", "true");

        properties.setProperty("mail.smtp.starttls.enable", "true"); // 启用TLS加密
        //邮箱授权认证(认证器)
        //创建认证器对象
        Auth auth = new Auth();
        //获取一个Session的会话对象
        Session session = Session.getDefaultInstance(properties, auth);
        //获取连接
        Transport transport = session.getTransport();
        //连接服务器
        transport.connect("smtp.qq.com", "july.lankai.vip@qq.com", "ztagtumfngjsfajg");
        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);
        //设置发件人地址
        mimeMessage.setFrom(new InternetAddress("july.lankai.vip@qq.com"));
        //设置收件人地址（单发
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        //群发邮件使用InternetAddress数组
        //InternetAddress[] addresses = {new InternetAddress("邮箱"),
        //   new InternetAddress("邮箱2")};
        //设置邮件标题
        mimeMessage.setSubject("华理轻小说邮箱验证码通知");
        //生成验证码
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10);  // 生成0-9之间的随机数
            code.append(digit);
        }
        String verificationCode = code.toString();
        String emailContent = "【华理轻小说】 验证码 " + verificationCode + "。用于华理轻小说账号注册，此验证码5分钟内有效，请勿泄露与转发。如非本人操作，请忽略此短信。";
        //设置邮件内容
        mimeMessage.setContent(emailContent, "text/html;charset=utf-8");
        //发送邮件
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        //关闭连接
        transport.close();
        //返回发送的验证码
        return verificationCode;
    }
}
