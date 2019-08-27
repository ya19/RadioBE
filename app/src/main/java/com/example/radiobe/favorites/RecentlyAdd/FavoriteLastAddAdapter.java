package com.example.radiobe.favorites.RecentlyAdd;

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

public class FavoriteLastAddAdapter extends RecyclerView.Adapter<FavoriteLastAddAdapter.FavoriteLastAddViewHolder> {
    /*Properties*/
    private List<FavoriteRecentlyAddItem> favoriteItemList;
    private Context context;

    /*Constructor*/
    public FavoriteLastAddAdapter(List<FavoriteRecentlyAddItem> favoriteItemList, Context context) {
        this.favoriteItemList = favoriteItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteLastAddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_favorite_my_favorite, parent, false);

        return new FavoriteLastAddViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteLastAddViewHolder holder, int position) {
        FavoriteRecentlyAddItem favoriteRecentlyAddItem = favoriteItemList.get(position);

        holder.ivFavoriteLastAddImage.setImageResource(favoriteRecentlyAddItem.getImageURL());
        holder.tvFavoriteLastAddTitle.setText(favoriteRecentlyAddItem.getTitle());
        holder.tvFavoriteLastAddDescription.setText(favoriteRecentlyAddItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return favoriteItemList.size();
    }

    class FavoriteLastAddViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFavoriteLastAddImage;
        TextView tvFavoriteLastAddTitle;
        TextView tvFavoriteLastAddDescription;

        private FavoriteLastAddViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivFavoriteLastAddImage = itemView.findViewById(R.id.civFavLastAddImage);
            this.tvFavoriteLastAddTitle = itemView.findViewById(R.id.tvFavLastAddTitle);
            this.tvFavoriteLastAddDescription = itemView.findViewById(R.id.tvFavLastAddImageDescription);
        }
    }
}
