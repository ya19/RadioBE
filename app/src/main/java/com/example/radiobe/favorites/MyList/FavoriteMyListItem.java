package com.example.radiobe.favorites.MyList;

import android.widget.ImageView;
import android.widget.TextView;

public class FavoriteMyListItem {
    /*Properties*/
    private int MyListImage;
    private String MyListTitle;
    private String MyListDescription;

    /*Constructor*/
    public FavoriteMyListItem(int myListImage, String myListTitle, String myListDescription) {
        this.MyListImage = myListImage;
        this.MyListTitle = myListTitle;
        this.MyListDescription = myListDescription;
    }
    /*Getters*/
    public int getMyListImage() {
        return MyListImage;
    }

    public String getMyListTitle() {
        return MyListTitle;
    }

    public String getMyListDescription() {
        return MyListDescription;
    }

    /*ToString*/
    @Override
    public String toString() {
        return "FavoriteMyListItem{" +
                "MyListImage=" + MyListImage +
                ", MyListTitle='" + MyListTitle + '\'' +
                ", MyListDescription='" + MyListDescription + '\'' +
                '}';
    }
}
