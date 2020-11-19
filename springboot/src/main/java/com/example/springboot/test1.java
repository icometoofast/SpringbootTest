package com.example.springboot;


import com.alibaba.fastjson.JSONObject;
import org.json.JSONException;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class test1 {
    @Value("${name}")
    private String name;

    @Value("${action}")
    private String action;

    public JSONObject postvalue(){
        JSONObject postit = new JSONObject();
        postit.put("msg", "ok");
        postit.put("method", "@ResponseBody");
        postit.put("name", name);
        return postit;
    }

    @RequestMapping("/test1")
    public String print(){
        return "hello world" + name + action;
    }

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public JSONObject get() throws JSONException {

        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "@ResponseBody");
        result.put("name", name);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public JSONObject post(@RequestBody JSONObject jsonParam) throws JSONException {
        String message;
        String userName = jsonParam.get("username").toString();
        String password = jsonParam.get("password").toString();
        if("username".equals(userName)&&"password".equals(password)){
            message = "welcome";
        }
        else {
            message = "sorry";
        }
        JSONObject result = new JSONObject();
        JSONObject result1 = new JSONObject();
        result1.put("password",password);
        result1.put("username",userName);
        result.put("msg", "ok");
        result.put("errCode", "0");
        result.put("username", userName);
        result.put("password",password);
        result.put("message",message);
        result.put("person",result1);
        return result;
    }
}

