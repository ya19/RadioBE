package com.example.radiobe.mainScreen;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.radiobe.R;
import com.example.radiobe.favorites.FavoriteDataSourceLastAdd;
import com.example.radiobe.favorites.FavoriteLastAddAdapter;
import com.example.radiobe.favorites.FavoritesRecommendedAdapter;
import com.example.radiobe.favorites.FavoritesDataSource;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Favorites extends Fragment {


    public Favorites() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //recycleView Top
        RecyclerView recyclerViewTop = view.findViewById(R.id.rvFavoriteTop);
        FavoritesRecommendedAdapter favoritesRecommendedAdapter = new FavoritesRecommendedAdapter(
                        FavoritesDataSource.getFavoritesItems(),getContext());
        //recycleView Middle
        RecyclerView recyclerViewMiddle = view.findViewById(R.id.rvFavoriteMiddle);
        FavoriteLastAddAdapter favoriteLastAddAdapter = new FavoriteLastAddAdapter(
                FavoriteDataSourceLastAdd.lastAddItems(),getContext());
        //recycleView Bottom
//        RecyclerView recyclerViewBottom = view.findViewById(R.id.rvFavoriteBottom);



        // the recyclerView is default Vertical so the LinearLayoutManager can switch it
        // to Horizontal by set the Orientation with LinearLayoutManager.HORIZONTAL
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,false);

        recyclerViewTop.setLayoutManager(layoutManager);
        recyclerViewTop.setAdapter(favoritesRecommendedAdapter);

        recyclerViewMiddle.setLayoutManager(layoutManager2);
        recyclerViewMiddle.setAdapter(favoriteLastAddAdapter);

    }
}
