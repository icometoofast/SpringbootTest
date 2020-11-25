package com.example.springboot.withHtml.mapper;


import com.example.springboot.withHtml.entities.User;
import com.example.springboot.withHtml.entities.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoAndImageMapper {
    List<Video> findVideoData();
    List<User> testTable(@Param("user") User user);
}
