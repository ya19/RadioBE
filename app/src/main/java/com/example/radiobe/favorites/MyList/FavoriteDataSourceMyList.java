package com.example.radiobe.favorites.MyList;

import com.example.radiobe.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDataSourceMyList {

    public static List<FavoriteMyListItem> myListItems() {
        List<FavoriteMyListItem> items = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            items.add(new FavoriteMyListItem(R.drawable.equalizer,
                    "Song Name",
                    "The description of the song category"));
            items.add(new FavoriteMyListItem(R.drawable.ic_google,
                    "Google",
                    "Google is everything"));
            items.add(new FavoriteMyListItem(R.drawable.ic_radio,
                    "Radio",
                    "The description"));
        }
        return items;
    }
}
