package com.example.springboot.withHtml.others;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
@PropertySource(value="classpath:properties/email.properties")
public class sendEmail {
    @Value("${simpleemail.sign.title}")
    private String title;

    @Value("${simpleemail.sendfrom}")
    private String sendfrom;

    @Value("${withHtml.forgetpwd.reset.url}")
    private String resetPwdUrl;


    public String getSendfrom() {
        return sendfrom;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSendfrom(String sendfrom) {
        this.sendfrom = sendfrom;
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public FreeMarkerConfigurer getFreeMarkerConfigurer() {
        return freeMarkerConfigurer;
    }

    public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;


    @Async
    public Integer sendSignEmail(String email,String verify) throws  IOException, TemplateException {

        Integer err = 1;
        try {
        MimeMessage mm = mailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mm);
        mmh.setSubject(title);
        mmh.setTo(email);
        mmh.setFrom(sendfrom);
        Template template1 = freeMarkerConfigurer.getConfiguration().getTemplate("signEmail.vm");
        // 使用Map作为数据模型，定义属性和值
        Map<String,Object> model = new HashMap<>();
        model.put("email",email);
        model.put("yzm",verify);
        // 传入数据模型到模板，替代模板中的占位符，并将模板转化为html字符串
        String templateHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template1,model);
        // 该方法本质上还是发送html邮件，调用之前发送html邮件的方法
        mmh.setText(templateHtml);
        mm.setContent(templateHtml, "text/html;charset=utf-8");
        mailSender.send(mm);
    } catch (MessagingException e) {
        e.printStackTrace();
        return 1;
    }

        return 0;

    }

    @Async
    public Integer sendForgetpwdEmail(String email) throws  IOException, TemplateException {

        Integer err = 1;
        try {
            MimeMessage mm = mailSender.createMimeMessage();
            MimeMessageHelper mmh = new MimeMessageHelper(mm);
            mmh.setSubject(title);
            mmh.setTo(email);
            mmh.setFrom(sendfrom);
            Template template1 = freeMarkerConfigurer.getConfiguration().getTemplate("resetPasswordEmail.vm");
            // 使用Map作为数据模型，定义属性和值
            Map<String,Object> model = new HashMap<>();
            model.put("email",email);
            model.put("resetPage",resetPwdUrl+email);

            // 传入数据模型到模板，替代模板中的占位符，并将模板转化为html字符串
            String templateHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template1,model);
            // 该方法本质上还是发送html邮件，调用之前发送html邮件的方法
            mmh.setText(templateHtml);
            mm.setContent(templateHtml, "text/html;charset=utf-8");
            System.out.println(templateHtml);
            mailSender.send(mm);
        } catch (MessagingException e) {
            e.printStackTrace();
            return 1;
        }

        return 0;

    }
}
