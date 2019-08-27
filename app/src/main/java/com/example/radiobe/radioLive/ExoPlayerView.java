//package com.example.radiobe.radioLive;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.localbroadcastmanager.content.LocalBroadcastManager;
//
//import com.example.radiobe.R;
//import com.example.radiobe.StreamDAO;
//import com.example.radiobe.models.RadioItem;
//import com.google.android.exoplayer2.ExoPlayerFactory;
//import com.google.android.exoplayer2.SimpleExoPlayer;
//import com.google.android.exoplayer2.source.ExtractorMediaSource;
//import com.google.android.exoplayer2.ui.PlayerView;
//import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
//
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//
//public class ExoPlayerView extends Fragment {
//
//
//
//    String fileName;
//    String filePath;
//    private PlayerView playerView;
//    private SimpleExoPlayer simpleExoPlayer;
//    //    String newFilePath = "";
////    private final String mediaPath = "https://be.repoai.com:5443/LiveApp/streams/vod/";
////    private String mediaBroadcast = "%D7%9E%D7%A9%D7%93%D7%A8_%D7%9E%D7%99%D7%95%D7%97%D7%93_%D7%9C%D7%9B%D7%91%D7%95%D7%93_%D7%99%D7%95%D7%9D_%D7%94%D7%96%D7%A2%D7%9B%D7%A8%D7%95%D7%9F_%D7%9C%D7%97%D7%9C%D7%9C%D7%99_%D7%9E%D7%A2%D7%A8%D7%9B%D7%95%D7%AA_%D7%99%D7%A9%D7%A8%D7%90%D7%9C_%D7%95%D7%A4%D7%A2%D7%95%D7%9C%D7%95%D7%AA_%D7%94%D7%90%D7%99%D7%91%D7%94.mp4";
//////    public static String mediaBroadcast = "%D7%9E%D7%A9%D7%93%D7%A8_%D7%9E%D7%99%D7%95%D7%97%D7%93_%D7%9C%D7%9B%D7%91%D7%95%D7%93_%D7%99%D7%95%D7%9D_%D7%94%D7%96%D7%A2%D7%9B%D7%A8%D7%95%D7%9F_%D7%9C%D7%97%D7%9C%D7%9C%D7%99_%D7%9E%D7%A2%D7%A8%D7%9B%D7%95%D7%AA_%D7%99%D7%A9%D7%A8%D7%90%D7%9C_%D7%95%D7%A4%D7%A2%D7%95%D7%9C%D7%95%D7%AA_%D7%94%D7%90%D7%99%D7%91%D7%94.mp4";
////    private Uri uri = Uri.parse(mediaPath+mediaBroadcast);
//    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//            System.out.println("Got Broadcast");
//            fileName = intent.getStringExtra("stream_name");
//            filePath = intent.getStringExtra("stream_url");
//            boolean play = intent.getBooleanExtra("play", false);
//            if(play)
//                loadDataToplayer(fileName, filePath);
//            else{
//                simpleExoPlayer.stop();
//            }
//
//        }
//    };
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.exoplayer_player_view, container, false);
//    }
//
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        setupViews(view);
//        //Set the Player to the PlayerView(xml)
//        playerView.setPlayer(simpleExoPlayer);
//
//
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver, new IntentFilter("play_song"));
//        System.out.println("Registered from onCreate");
//    }
//
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
//    }
//
//
////    @Override
////    public void onAttach(@NonNull Context context) {
////        super.onAttach(context);
////        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver, new IntentFilter("play_song"));
////
////    }
//
//    private void loadDataToplayer(String fileName, String filePath) {
////        newFilePath = getArguments().getString("filePath");
//
////        filePath = "https://be.repoai.com:5443/LiveApp/streams/vod/אנשים_תכנית_שניה.mp4";
//        //Load DataSorce Uri
//        ExtractorMediaSource uriMediaSource =
//                new ExtractorMediaSource.Factory(
//                        new DefaultHttpDataSourceFactory("Radio be")).
//                        createMediaSource(Uri.parse(filePath));
//
//        //Prepare the exoPlayerInstance with the source and play when he Ready
//
//        simpleExoPlayer.prepare(uriMediaSource);
//        simpleExoPlayer.setPlayWhenReady(true);
//    }
//
//
//    private void setupViews(View view) {
//        playerView = view.findViewById(R.id.player_view);
//        // Setup Exoplayer instance
//        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext());
//
//    }
//}
//
//
