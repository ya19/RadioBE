package com.example.radiobe.radio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.radiobe.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RadioItemsAdapter extends RecyclerView.Adapter<RadioItemsAdapter.RadioViewHolder> {
    List<RadioItem> items;
    Context context;
    public static List<RadioItem> filteredByShahaf = new ArrayList<>();
    public static List<RadioItem> filteredByYarden = new ArrayList<>();
    public static List<RadioItem> filteredBySDaniel = new ArrayList<>();
//    private MyItemsRepository myItemsRepository;

    public RadioItemsAdapter(List<RadioItem> items, Context context) {

        this.items = items;
        this.context = context;
    }


    @NonNull
    @Override
    public RadioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View viewItem = inflater.inflate(R.layout.item_radio, parent ,false);

        RadioViewHolder holder = new RadioViewHolder(viewItem);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RadioViewHolder holder, int position) {
        RadioItem radioItem = items.get(position);
        holder.tvShadran.setText(radioItem.getShadran());
        holder.tvDuration.setText(radioItem.getDuration());
        holder.tvAdded.setText(radioItem.getAdded());
        holder.tvViews.setText(String.valueOf(radioItem.getViews()));
        holder.tvComments.setText(String.valueOf(radioItem.getComments()));
        holder.tvLikes.setText(String.valueOf(radioItem.getLikes()));

        //holder.tb.setBackgroundResource(radioItem.getResImage());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class RadioViewHolder extends RecyclerView.ViewHolder{
        ToggleButton tb;
        TextView tvShadran;
        TextView tvDuration;
        TextView tvAdded;
        FloatingActionButton addFavorites;
        FloatingActionButton shareFacebook;
        ImageButton addLike;
        ImageButton addComment;
        ImageView ivViews;
        TextView tvLikes;
        TextView tvComments;
        TextView tvViews;


        public RadioViewHolder(@NonNull View itemView) {
            super(itemView);
            tb = itemView.findViewById(R.id.tbPlayStop);
            tvShadran = itemView.findViewById(R.id.shadranTv);
            tvDuration = itemView.findViewById(R.id.durationTv);
            tvAdded = itemView.findViewById(R.id.addedTv);
            addFavorites = itemView.findViewById(R.id.addFavoriteBtn);
            shareFacebook = itemView.findViewById(R.id.shareFbBtn);
            addLike = itemView.findViewById(R.id.likeBtn);
            addComment = itemView.findViewById(R.id.commentBtn);
            ivViews = itemView.findViewById(R.id.viewsIv);
            tvLikes = itemView.findViewById(R.id.likesTv);
            tvComments = itemView.findViewById(R.id.commentsTv);
            tvViews = itemView.findViewById(R.id.viewsTv);
        }
    }
}
