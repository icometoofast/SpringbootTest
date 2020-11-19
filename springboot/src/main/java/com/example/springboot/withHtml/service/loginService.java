package com.example.springboot.withHtml.service;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.withHtml.mapper.loginAndSignAndForgetpwdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loginService {
    @Autowired
    private loginAndSignAndForgetpwdMapper loginmapper;

    public JSONObject login(JSONObject json){
        String email = json.get("email").toString();
        String password = json.get("password").toString();
        JSONObject result = new JSONObject();
        result.put("number",loginmapper.login(email,password));
        return result;
    }

}
