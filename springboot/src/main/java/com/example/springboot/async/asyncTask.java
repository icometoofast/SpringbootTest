package com.example.springboot.async;

import com.alibaba.fastjson.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class asyncTask {

    @Async
    public JSONObject task1() throws InterruptedException {
        Thread t = new Thread();
        Random r = new Random();
        int ran = r.nextInt(10000);
        System.out.println("随机数是："+ran);
        long start = System.currentTimeMillis();
        t.sleep(ran);
        long end = System.currentTimeMillis();
        long time = end - start;
        JSONObject json = new JSONObject();
        json.put("task","task1");
        json.put("start",start);
        json.put("end",end);
        json.put("time",time);
        System.out.println("task1完成");
        System.out.println("未回传前"+json.toString());
        return json;
    }

    @Async
    public JSONObject task2() throws InterruptedException {
        Thread t = new Thread();
        Random r = new Random();
        int ran = r.nextInt(4000);
        long start = System.currentTimeMillis();
        t.sleep(ran);
        long end = System.currentTimeMillis();
        long time = end - start;
        JSONObject json = new JSONObject();
        json.put("task","task2");
        json.put("start",start);
        json.put("end",end);
        json.put("time",time);
        System.out.println("task2完成");
        System.out.println("未回传前"+json.toString());
        return json;
    }
    @Async
    public JSONObject task3() throws InterruptedException {
        Thread t = new Thread();
        Random r = new Random();
        int ran = r.nextInt(2000);
        long start = System.currentTimeMillis();
        t.sleep(ran);
        long end = System.currentTimeMillis();
        long time = end - start;
        JSONObject json = new JSONObject();
        json.put("task","task3");
        json.put("start",start);
        json.put("end",end);
        json.put("time",time);
        System.out.println("task3完成");
        System.out.println("未回传前"+json.toString());
        return json;
    }
}
