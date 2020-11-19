package com.example.springboot.ConnDBWithMybatis;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.sendEmail.sendEmailTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CdbwmController{
    @Autowired
    private CdbwmService cdbwmservice;
    @Autowired
    private sendEmailTest se;

    @RequestMapping("/selectUser")
    public JSONObject getUserInfo(){
        JSONObject result = new JSONObject();
        result.put("number",cdbwmservice.returnNumber());
        result.put("msg","seccess");
        result.put("data",cdbwmservice.selectUser());
        return result;

    }

    @RequestMapping("/selectUsers")
    public JSONObject getUsersInfo(){
        JSONObject result = new JSONObject();
        result.put("msg","success");
        result.put("number",cdbwmservice.returnNumbers());
        result.put("data",cdbwmservice.selectUsers());
        return result;
    }

    @RequestMapping(value = "/selectUserById/{name}",method = RequestMethod.GET)
    public JSONObject selectUserById(@PathVariable(value = "name",required = false) String name){

        JSONObject result = new JSONObject();
        result.put("name",name);
        result.put("number",cdbwmservice.returnNumberById(name));
        result.put("data",cdbwmservice.selectUserById(name));
        return result;
    }

    @RequestMapping(value = "/selectUserByKV",method = RequestMethod.GET)
    public JSONObject selectUserByKV(@RequestParam(value = "name",required = false) String name){

        JSONObject result = new JSONObject();
        result.put("name",name);
        result.put("number",cdbwmservice.returnNumberById(name));
        result.put("data",cdbwmservice.selectUserById(name));
        return result;
    }


    @RequestMapping(value = "/insertDataSan",method = RequestMethod.POST)
    public JSONObject insertDataSan(@RequestBody JSONObject json){
        JSONObject result = new JSONObject();
        if(json.get("name") != null && json.get("sex") != null && json.get("age") != null) {
            String name = json.get("name").toString();
            String sex = json.get("sex").toString();
            int age = Integer.parseInt(json.get("age").toString());
            int a = cdbwmservice.insertDataSan(name, sex, age);
            String message = "something wrong";
            if (a == 1) {
                message = "success";
            }
            result.put("message", message);
            result.put("data",json);
        }
        else{
            result.put("message","your input is wrong");
        }

            return result;


    }

    @RequestMapping(value = "/insertDataUser",method = RequestMethod.POST)
    public JSONObject insertDataUser(@RequestBody JSONObject json){
        JSONObject result = new JSONObject();
        if(json.get("name") != null && json.get("sex") != null && json.get("age") != null) {
            String name = json.get("name").toString();
            String sex = json.get("sex").toString();
            int age = Integer.parseInt(json.get("age").toString());
            int a = cdbwmservice.insertDataSan(name, sex, age);
            String message = "something wrong";
            if (a == 1) {
                message = "success";
            }
            result.put("message", message);
            result.put("data",json);
        }
        else{
            result.put("message","your input is wrong");
        }
        return result;
    }


}

    /**
     开发一个需要携带参数才能访问的get请求
     第一种 url：?key=value&key=value
     **/
//    @GetMapping(value = "/get/with") url为localhost：8080/get/with?key=value
//    public Map<String,Integer> getlist2(
//            @RequestParam (value = "start",required = false)int start,
//            @RequestParam (value = "end",required =false )int end){
//
//        Map<String,Integer>map=new HashMap<>();
//        map.put("干脆面",100);
//        map.put("衣服",200);
//        map.put("鞋子",200);
//        return map;
//    }

    /**
     * 第二种携带参数访问的请求
     * url:port/get/with/param/10/20
     */
//    @GetMapping(value = "/get/with/param/{start}/{end}")
//    public Map<String,Integer> getlist(
//            @PathVariable(value = "start",required = false)int start,
//            @PathVariable (value = "end",required =false )int end){
//
//        Map<String,Integer>map=new HashMap<>();
//        map.put("干脆面",100);
//        map.put("衣服",200);
//        map.put("鞋子",200);
//        return map;
//    }

