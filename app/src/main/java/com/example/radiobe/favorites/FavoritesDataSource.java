package com.example.radiobe.favorites;

import com.example.radiobe.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritesDataSource {
    //we need to use API

    //temp source
    public static List<FavoriteRecommendedItem> getFavoritesItems() {
        List<FavoriteRecommendedItem> items = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            items.add(new FavoriteRecommendedItem(R.drawable.equalizer,
                    "Song Name",
                    "The description of the song category"));
            items.add(new FavoriteRecommendedItem(R.drawable.ic_google,
                    "Google",
                    "Google is everything"));
            items.add(new FavoriteRecommendedItem(R.drawable.ic_radio,
                    "Radio",
                    "The description"));
        }

        return items;
    }
}
