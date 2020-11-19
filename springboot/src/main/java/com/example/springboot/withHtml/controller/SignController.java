package com.example.springboot.withHtml.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.springboot.withHtml.service.signService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
public class SignController {

    @Autowired
    private signService signservice;

    @RequestMapping(value = "/sendEmail/{email}",method = RequestMethod.GET)
    public JSONObject sendEmail(@PathVariable String email) throws MessagingException, IOException, TemplateException {
        return signservice.sendEmailService(email);
    }

    @RequestMapping(value = "/sign" , method = RequestMethod.POST)
    public JSONObject sign(@RequestBody JSONObject json) {
        return signservice.signservice(json);
    }
}
