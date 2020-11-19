package com.example.springboot.withHtml.service;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.withHtml.mapper.loginAndSignAndForgetpwdMapper;
import com.example.springboot.withHtml.others.sendEmail;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class forgetpwdService {

    @Autowired
    private loginAndSignAndForgetpwdMapper loginAndSignAndForgetpwdMapper;

    @Autowired
    private sendEmail sendEmail;

    public JSONObject forgetpwd(JSONObject json) throws IOException, TemplateException {
        JSONObject result = new JSONObject();
        String email = json.getString("email");
        int number = loginAndSignAndForgetpwdMapper.isRepeatSign(email);
        if(number == 1){
            result.put("err",0);
            Integer a = sendEmail.sendForgetpwdEmail(email);


        }else if(number == 0){
            result.put("err",1);
        }

        return result;
    }
}
