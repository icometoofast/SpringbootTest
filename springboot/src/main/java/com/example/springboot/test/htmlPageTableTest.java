package com.example.springboot.test;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class htmlPageTableTest {


    @RequestMapping(value = "/getBedTest", method = RequestMethod.POST)
    public JSONObject getBadTest(@RequestBody JSONObject json){
        JSONObject result = new JSONObject();
        result.put("total",4);
        List<JSONObject> datalist = new ArrayList<JSONObject>();
//        JSONObject data = new JSONObject();
        for(int i = 1;i <= 4; i++){
            JSONObject data = new JSONObject();
            data.put("id",i);
            data.put("username","username"+i);
            data.put("sex","male");

            System.out.println("========================");
            System.out.println(data);
            datalist.add(data);
            System.out.println("------------------------");
            System.out.println(datalist);

        }
        result.put("data",datalist);
        return result ;
    }
}
