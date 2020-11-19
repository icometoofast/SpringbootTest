package com.example.springboot.withHtml.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.example.springboot.withHtml.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//TODO 页面适配手机浏览器

@RestController
public class LoginController {

    @Autowired
    private loginService loginservice;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JSONObject login(@RequestBody JSONObject json){
        return loginservice.login(json);
    }
}
