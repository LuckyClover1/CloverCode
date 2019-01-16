package com.clover.enumEntity;

import java.util.Date;

public class QiubaiInfo {
    private int id;
    private int authorId;
    private String title;
    private Date publishTime;
    private String haoxiaoNum;
    private String publishSource;
    private String content;
    private String images;
    private String videoes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getHaoxiaoNum() {
        return haoxiaoNum;
    }

    public void setHaoxiaoNum(String haoxiaoNum) {
        this.haoxiaoNum = haoxiaoNum;
    }

    public String getPublishSource() {
        return publishSource;
    }

    public void setPublishSource(String publishSource) {
        this.publishSource = publishSource;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getVideoes() {
        return videoes;
    }

    public void setVideoes(String videoes) {
        this.videoes = videoes;
    }
}
