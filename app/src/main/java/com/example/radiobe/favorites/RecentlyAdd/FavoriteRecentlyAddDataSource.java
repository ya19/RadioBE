package com.example.radiobe.favorites.RecentlyAdd;

import com.example.radiobe.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteRecentlyAddDataSource {

    public static List<FavoriteRecentlyAddItem> lastAddItems() {
        List<FavoriteRecentlyAddItem> items = new ArrayList<>();

        for (int i = 0; i <50 ; i++) {
            items.add(new FavoriteRecentlyAddItem(R.drawable.equalizer,
                    "Song Name",
                    "The description of the song category"));
            items.add(new FavoriteRecentlyAddItem(R.drawable.ic_google,
                    "Google",
                    "Google is everything"));
            items.add(new FavoriteRecentlyAddItem(R.drawable.ic_radio,
                    "Radio",
                    "The description"));
        }

        return items;
    }
}
