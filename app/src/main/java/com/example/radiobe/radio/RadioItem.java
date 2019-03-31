package com.example.radiobe.radio;

public class RadioItem {

    //props
    private String shadran;
    private String duration;
    private String added;
    private int likes;
    private int views;
    private int comments;
    private int resImage;



    //empty ctor?
    public RadioItem(){}

    //ctor

    public RadioItem(String shadran, String duration, String added, int likes, int views, int comments, int resImage) {
        this.shadran = shadran;
        this.duration = duration;
        this.added = added;
        this.likes = likes;
        this.views = views;
        this.comments = comments;
        this.resImage = resImage;
    }

    //getters setters
    public String getShadran() {
        return shadran;
    }
    public void setShadran(String shadran) {
        this.shadran = shadran;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getAdded() {
        return added;
    }
    public void setAdded(String added) {
        this.added = added;
    }
    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }
    public int getViews() {
        return views;
    }
    public void setViews(int views) {
        this.views = views;
    }
    public int getComments() {
        return comments;
    }
    public void setComments(int comments) {
        this.comments = comments;
    }
    public int getResImage() {
        return resImage;
    }
    public void setResImage(int resImage) {
        this.resImage = resImage;
    }


    @Override
    public String toString() {
        return "RadioItem{" +
                "shadran='" + shadran + '\'' +
                ", duration='" + duration + '\'' +
                ", added='" + added + '\'' +
                ", likes=" + likes +
                ", views=" + views +
                ", comments=" + comments +
                ", resImage=" + resImage +
                '}';
    }
}
