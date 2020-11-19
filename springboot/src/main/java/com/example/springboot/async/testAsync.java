package com.example.springboot.async;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.util.Random;

/*
记一下有关异步的注意点，
1. 首先异步方法上面要有@async注解，主程序上要有@enableasync注解。
2. 其次，要异步执行的程序与调用他们的程序不能再同一个类里面
3. 最后，只能通过自动装配的方式将异步执行的程序所在的类实例化，如果是new方法的化是不会进行异步操作的
4. 还没有明确原因，在使用异步调用的时候，json类型的返回值会变成null
5. 关于4没有返回值的原因，是因为异步，所以方法一起动，导致直接返回了空的，尝试过暂停，但是无效
以上
 */

@RestController
public class testAsync {

    @Autowired
    private asyncTask task;

    @RequestMapping("/testAnyc")
    public JSONObject tesAnyc() throws InterruptedException {
        JSONObject result = new JSONObject();
//
//        JSONObject result1 = task.task1();
//        JSONObject result2 = task.task2();
//        JSONObject result3 = task.task3();
//        result.put("task1",result1);
//        result.put("task2",result2);
//        result.put("task3",result3);

        result.put("task1",task.task1());
        result.put("task2",task.task2());
        result.put("task3",task.task3());
//        System.out.println("回传后"+result1.toString());
//        System.out.println("回传后"+result2.toString());
//        System.out.println("回传后"+result3.toString());
        return result;


    }


}
