//package com.example.radiobe;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.ToggleButton;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.android.material.tabs.TabLayout;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.DiffUtil;
//import androidx.recyclerview.widget.ListAdapter;
//import androidx.recyclerview.widget.RecyclerView;
//
//import static com.example.radiobe.radio.MyItemsRepository.radioItems;
//
//public class RadioItemsNewAdapter extends ListAdapter<RadioItem, RadioItemsNewAdapter.RadioViewHolder> {
////    List<RadioItem> items;
//    Context context;
//    RadioItem radioItem;
//
//    public RadioItemsNewAdapter() {
//        super(DIFF_CALLBACK);
//    }
//
//
//    private static final DiffUtil.ItemCallback<RadioItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<RadioItem>() {
//        @Override
//        public boolean areItemsTheSame(@NonNull RadioItem oldItem, @NonNull RadioItem newItem) {
//            return oldItem.getId() == newItem.getId();
//        }
//
//        @Override
//        public boolean areContentsTheSame(@NonNull RadioItem oldItem, @NonNull RadioItem newItem) {
//            return oldItem.getShadran().equals(newItem.getShadran()) &&
//                    oldItem.getAdded().equals(newItem.getAdded()) &&
//                    oldItem.getLikes() == newItem.getLikes() &&
//                    oldItem.getViews() == newItem.getViews() &&
//                    oldItem.getComments() == newItem.getComments() &&
//                    oldItem.getDuration().equals(newItem.getDuration()) &&
//                    oldItem.getResImage() == newItem.getResImage() &&
//                    oldItem.getId() == newItem.getId();
//        }
//     };
////    public static List<RadioItem> filteredByShahaf = new ArrayList<>();
////    public static List<RadioItem> filteredByYarden = new ArrayList<>();
////    public static List<RadioItem> filteredBySDaniel = new ArrayList<>();
////    private MyItemsRepository myItemsRepository;
//
//
//    public RadioItem getRadioItemAt(int position){
//        return getItem(position);
//    }
//
//    @NonNull
//    @Override
//    public RadioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//
//        View viewItem = inflater.inflate(R.layout.item_radio, parent ,false);
//
//        RadioViewHolder holder = new RadioViewHolder(viewItem);
//
//        return holder;
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RadioViewHolder holder, int position) {
//        RadioItem radioItem = getItem(position);
//        holder.tvShadran.setText(radioItem.getShadran());
//        holder.tvDuration.setText(radioItem.getDuration());
//        holder.tvAdded.setText(radioItem.getAdded());
//        holder.tvViews.setText(String.valueOf(radioItem.getViews()));
//        holder.tvComments.setText(String.valueOf(radioItem.getComments()));
//        holder.tvLikes.setText(String.valueOf(radioItem.getLikes()));
//
//        //holder.tb.setBackgroundResource(radioItem.getResImage());
//
//        AllPrograms.tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch(tab.getPosition()){
//                    case 0:
//                        break;
//                    case 1:
////                        filteredByYarden = MyItemsRepository.filterByYarden(radioItems);
//
////                        recyclerView.setAdapter(new RadioItemsAdapter(new MyItemsRepository().filterByName(RadioItemsDataSource.getItems()), getContext()));
//                        break;
//                    case 2:
////                        filteredByYarden = MyItemsRepository.filterByYarden(radioItems);
//                        break;
//                    case 3:
//                        break;
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//    }
//
//    class RadioViewHolder extends RecyclerView.ViewHolder{
//        ToggleButton tb;
//        TextView tvShadran;
//        TextView tvDuration;
//        TextView tvAdded;
//        FloatingActionButton addFavorites;
//        FloatingActionButton shareFacebook;
//        ImageButton addLike;
//        ImageButton addComment;
//        ImageView ivViews;
//        TextView tvLikes;
//        TextView tvComments;
//        TextView tvViews;
//
//
//        public RadioViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tb = itemView.findViewById(R.id.tbPlayStop);
//            tvShadran = itemView.findViewById(R.id.shadranTv);
//            tvDuration = itemView.findViewById(R.id.durationTv);
//            tvAdded = itemView.findViewById(R.id.addedTv);
//            addFavorites = itemView.findViewById(R.id.addFavoriteBtn);
//            shareFacebook = itemView.findViewById(R.id.shareFbBtn);
//            addLike = itemView.findViewById(R.id.likeBtn);
//            addComment = itemView.findViewById(R.id.commentBtn);
//            ivViews = itemView.findViewById(R.id.viewsIv);
//            tvLikes = itemView.findViewById(R.id.likesTv);
//            tvComments = itemView.findViewById(R.id.commentsTv);
//            tvViews = itemView.findViewById(R.id.viewsTv);
//        }
//    }
//}
