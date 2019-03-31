package com.example.radiobe.favorites;

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
    private List<FavoriteLastAddItem> favoriteItemList;
    private Context context;

    /*Constructor*/
    public FavoriteLastAddAdapter(List<FavoriteLastAddItem> favoriteItemList, Context context) {
        this.favoriteItemList = favoriteItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteLastAddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_favorite_last_add, parent, false);

        return new FavoriteLastAddViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteLastAddViewHolder holder, int position) {
        FavoriteLastAddItem favoriteLastAddItem = favoriteItemList.get(position);

        holder.ivFavoriteLastAddImage.setImageResource(favoriteLastAddItem.getImageURL());
        holder.tvFavoriteLastAddTitle.setText(favoriteLastAddItem.getTitle());
        holder.tvFavoriteLastAddDescription.setText(favoriteLastAddItem.getDescription());
    }



    @Override
    public int getItemCount() {
        return 0;
    }

    class FavoriteLastAddViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFavoriteLastAddImage;
        TextView tvFavoriteLastAddTitle;
        TextView tvFavoriteLastAddDescription;

        public FavoriteLastAddViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivFavoriteLastAddImage = itemView.findViewById(R.id.civFavoriteItemImage);
            this.tvFavoriteLastAddTitle = itemView.findViewById(R.id.tvItemFavoriteTitle);
            this.tvFavoriteLastAddDescription = itemView.findViewById(R.id.tvItemFavoriteDescription);
        }
    }
}
