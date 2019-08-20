package com.example.radiobe.models;

public class NotificationItem {
    /*Properties*/
    private int imageURL;
    private String title;
    private String description;
    private String date;

    /*Constructor*/
    public NotificationItem(int imageURL, String title, String description, String date) {
        this.imageURL = imageURL;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    /*Getters*/
    public int getImageURL() {
        return imageURL;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getDate() {
        return date;
    }

    /*ToString*/
    @Override
    public String toString() {
        return "NotificationItem{" +
                "imageURL='" + imageURL + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
