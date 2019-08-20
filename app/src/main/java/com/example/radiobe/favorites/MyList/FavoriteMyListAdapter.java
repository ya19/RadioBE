package com.example.radiobe.favorites.MyList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.radiobe.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteMyListAdapter extends RecyclerView.Adapter<FavoriteMyListAdapter.FavoriteMyListViewHolder> {
    private List<FavoriteMyListItem> favoriteDataSourceMyLists;
    private Context context;

    public FavoriteMyListAdapter(List<FavoriteMyListItem> favoriteDataSourceMyLists, Context context) {
        this.favoriteDataSourceMyLists = favoriteDataSourceMyLists;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteMyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_favorite_my_list, parent, false);

        return new FavoriteMyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMyListViewHolder holder, int position) {
        FavoriteMyListItem favoriteMyListItem = favoriteDataSourceMyLists.get(position);

        holder.civFavorMyListImage.setImageResource(favoriteMyListItem.getMyListImage());
        holder.tvFavorMyListTitle.setText(favoriteMyListItem.getMyListTitle());
        holder.tvFavorMyListDescription.setText(favoriteMyListItem.getMyListDescription());

    }

    @Override
    public int getItemCount() {
        return favoriteDataSourceMyLists.size();
    }

    class FavoriteMyListViewHolder extends RecyclerView.ViewHolder {
        ImageView civFavorMyListImage;
        TextView tvFavorMyListTitle;
        TextView tvFavorMyListDescription;

        private FavoriteMyListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.civFavorMyListImage = itemView.findViewById(R.id.civFavorMyListImage);
            this.tvFavorMyListTitle = itemView.findViewById(R.id.tvFavorMyListTitle);
            this.tvFavorMyListDescription = itemView.findViewById(R.id.tvFavorMyListDescription);
        }
    }

}
