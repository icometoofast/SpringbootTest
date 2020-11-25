package com.example.springboot.withHtml.service;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.withHtml.entities.Video;
import com.example.springboot.withHtml.mapper.VideoAndImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//todo
@Service
public class videoService {

    @Autowired
    private VideoAndImageMapper videoAndImageMapper;

    public JSONObject findVideoData(){
        JSONObject result = new JSONObject();
        List<Video> videos = videoAndImageMapper.findVideoData();
        for (Video video:videos) {
            System.out.println(video.getVideoName());
        }
        int total = videos.size();
        result.put("total",total);
        result.put("data",videos);
        return result;
    }
}
