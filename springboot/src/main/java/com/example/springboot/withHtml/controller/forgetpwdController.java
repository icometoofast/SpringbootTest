package com.example.springboot.withHtml.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.withHtml.service.forgetpwdService;
import freemarker.template.TemplateException;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//todo
@RestController
public class forgetpwdController {

    @Autowired
    private forgetpwdService forgetpwdservice;


    @RequestMapping(value="/forgetpwd", method = RequestMethod.POST)
    public JSONObject forgetpwd(@RequestBody JSONObject json) throws IOException, TemplateException {

    return forgetpwdservice.forgetpwd(json);
    }


}
