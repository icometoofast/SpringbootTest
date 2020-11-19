package com.example.springboot.withHtml.service;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.withHtml.mapper.loginAndSignAndForgetpwdMapper;
import com.example.springboot.withHtml.others.VerifyCode;
import com.example.springboot.withHtml.others.sendEmail;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Service
@PropertySource(value = "classpath:properties/email.properties")
public class signService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private loginAndSignAndForgetpwdMapper loginAndSignAndForgetpwdMapper;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;


    @Autowired
    private JavaMailSender mailSender;


    private VerifyCode verifycode;


    @Value("${sign.title}")
    private String title;

    @Value("${simpleemail.sendfrom}")
    private String sendfrom;

    @Autowired
    private sendEmail sendemail;

    public JSONObject sendEmailService(String email) throws MessagingException, IOException, TemplateException {
        JSONObject result = new JSONObject();

        if(loginAndSignAndForgetpwdMapper.isRepeatSign(email) >= 1)
        {
            result.put("err",1);
            result.put("message","repeatsign");
            return result;
        }else {
            //将验证吗以及邮箱信息填入redis中

            String verify = verifycode.generateVerifyCode(6);
            stringRedisTemplate.opsForValue().set(email, verify, 300, TimeUnit.SECONDS);
            sendemail.sendSignEmail(email, verify);
            result.put("err", 0);

            return result;
        }
    }

    public JSONObject signservice(JSONObject json){
        String email = json.getString("email");
        String password = json.getString("password");
        String yzm = json.getString("yzm");
        String verify = stringRedisTemplate.opsForValue().get(email);
        JSONObject result = new JSONObject();
        if(verify.equals(yzm)){
            int success = loginAndSignAndForgetpwdMapper.insertEmailAndPwd(email,password);
            if(success == 1){
                result.put("number",1);
            }else {
                result.put("number", 0);
            }
        }else {
            result.put("number",0);
        }
        return result;
    }
}
