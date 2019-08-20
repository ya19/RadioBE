package com.example.radiobe.favorites.Recommended;

import androidx.annotation.NonNull;

public class FavoriteRecommendedItem {
    /*Properties*/
    private int imageURL;
    private String title;
    private String description;

    /*Constructor*/
    public FavoriteRecommendedItem(int imageURL, String title, String description) {
        this.imageURL = imageURL;
        this.title = title;
        this.description = description;
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

    /*ToString*/
    @Override
    public String toString() {
        return "FavoriteRecommendedItem{" +
                "imageURL=" + imageURL +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
