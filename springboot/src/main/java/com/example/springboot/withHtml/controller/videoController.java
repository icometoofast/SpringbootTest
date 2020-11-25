package com.example.springboot.withHtml.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.withHtml.service.videoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class videoController {

    @Autowired
    private videoService videoservice;

    @RequestMapping(value = "/videoData", method = RequestMethod.GET)
    public JSONObject findVideoData(){
        return videoservice.findVideoData();
    }

}
