package com.example.springboot.sendEmail;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.file.FileSystem;

@Component
@RestController
@PropertySource(value = {"classpath:properties/email.properties"})
public class sendEmailTest {


    @Autowired
    private JavaMailSender mailSender;

    private int success = 0;


    @Value("${simpleemail.sendfrom}")
    private String sendfrom = "2636906994@qq.com";
    @Value("${simpleemail.sendto}")
    private String sendto = "2636906994@qq.com";
    @Value("${simpleemail.sign.title}")
    private String title = "this is the title of the email";
    @Value("${simpleemail.main}")
    private String main = "this is the main words of the email";

    @RequestMapping("sendSimpleEmail")
    public JSONObject sendEmailMethod() throws Exception{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendfrom);
        message.setTo(sendto);
        System.out.println(sendfrom+"to"+sendto);
        message.setSubject(title);
        message.setText(main);
        mailSender.send(message);
        success = 1;
        JSONObject json = new JSONObject();
        json.put("sendto",sendto);
        json.put("sendfrom",sendfrom);
        json.put("main",main);
        json.put("title",title);
        json.put("message",success);
        return json;


    }

    @RequestMapping(value = "/sendMoreEmail/{sendto2}",method = RequestMethod.GET)
    public JSONObject sendMoreEmail(@PathVariable(value = "sendto2",required = false) String sendto2) throws MessagingException {
        MimeMessage mm = mailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mm,true);
        mmh.setFrom(sendfrom);
        //发送给复数用户
        String[] sendUsers = new String[]{sendto2,sendto};
        mmh.setTo(sendUsers);
        mmh.setSubject("title:an Email with annex");

        //注意，这里是指创建一个新的文件fsr，
        // 并不是在邮件中插入这个地址的东西，
        // 同时，他的地址一定得是在计算机上的绝对地址，无法将项目下的相对机制作为文件地址，不然会报错

        FileSystemResource fsr1 = new FileSystemResource(new File("/Users/zhengfei.wang/Documents/springboot/src/main/resources/static/images/picture1.jpeg"));
        FileSystemResource fsr2 = new FileSystemResource(new File("/Users/zhengfei.wang/Documents/springboot/src/main/resources/static/images/picture2.jpg"));
        FileSystemResource fsr3 = new FileSystemResource(new File("/Users/zhengfei.wang/Documents/springboot/src/main/resources/static/images/picture3.jpg"));
        mmh.addInline("picture1",fsr1);
        //给附件起名，并将其作为邮件的附件
        mmh.addAttachment("picture1.jpeg",fsr1);
        mmh.addAttachment("picture2.jpg",fsr2);
        mmh.addAttachment("picture3.jpg",fsr3);
        String text = "<html><body><img src='cid:picture1'></body></html>";
        mm.setContent(text,"text/html;charset=utf-8");
        mmh.setText(text);
//        mm.setContent(text,"text/html;charset=utf-8");
        //发送邮件
        mailSender.send(mm);
        for (String sentToThese:sendUsers) {
            System.out.println(sendfrom+"to"+sentToThese);
        }

        JSONObject json = new JSONObject();
        json.put("sendto",sendUsers);
        json.put("sendfrom",sendfrom);
        json.put("main",main);
        json.put("title",title);
        json.put("message",success);
        return json;
    }
}
