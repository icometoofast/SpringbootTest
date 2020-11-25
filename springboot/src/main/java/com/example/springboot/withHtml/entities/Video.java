package com.example.springboot.withHtml.entities;


import org.springframework.stereotype.Component;

@Component
public class Video {
    private int id;
    private String videoName;
    private String videoUrl;
    private String videoImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String viderName) {
        this.videoName = viderName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(String videoImage) {
        this.videoImage = videoImage;
    }
}
