package com.example.radiobe.favorites;

import com.example.radiobe.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDataSourceLastAdd {

    public static List<FavoriteLastAddItem> lastAddItems() {
        List<FavoriteLastAddItem> items = new ArrayList<>();

        for (int i = 0; i <50 ; i++) {
            items.add(new FavoriteLastAddItem(R.drawable.equalizer,
                    "Song Name",
                    "The description of the song category"));
            items.add(new FavoriteLastAddItem(R.drawable.ic_google,
                    "Google",
                    "Google is everything"));
            items.add(new FavoriteLastAddItem(R.drawable.ic_radio,
                    "Radio",
                    "The description"));
        }

        return items;
    }
}
