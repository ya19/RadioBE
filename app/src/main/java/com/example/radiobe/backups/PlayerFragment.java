//package com.example.radiobe.radioLive;
//
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v4.media.MediaBrowserCompat;
//import android.support.v4.media.MediaMetadataCompat;
//import android.support.v4.media.session.MediaControllerCompat;
//import android.support.v4.media.session.MediaSessionCompat;
//import android.support.v4.media.session.PlaybackStateCompat;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.radiobe.R;
//
//import java.util.List;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class PlayerFragment extends Fragment {
//
//    public ImageView bnPlay;
//    private ImageView bnBack;
//    private ImageView bnForward;
//    private ImageView bnMute;
//    private TextView tvProgramName;
//    private TextView tvStudentName;
//    private TextView tvPlayerLine;
//    private MediaSeekBar sbVolumeSong;
//    private TextView tvTime;
//    private ProgressBar pbLoading;
//
//    private MediaBrowserHelper mMediaBrowserHelper;
//
//    static public boolean mIsPlaying;
////    static public boolean mIsLoading;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.frag_player, container, false);
//
//        setupViews(view);
////        initProgressBar(view);
//
//        bnBack.setOnClickListener(v -> bnBack.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).withEndAction(() -> {
//            bnBack.animate().scaleX(1).scaleY(1).setDuration(200);
//            mMediaBrowserHelper.getTransportControls().skipToPrevious();
//        }));
//
//        bnForward.setOnClickListener(v -> bnForward.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).withEndAction(() -> {
//            bnForward.animate().scaleX(1).scaleY(1).setDuration(200);
//            mMediaBrowserHelper.getTransportControls().skipToNext();
//        }));
//
//        bnPlay.setOnClickListener(v -> playFunction());
//
//        bnMute.setOnClickListener(v -> muteFunction());
//
//        mMediaBrowserHelper = new PlayerFragment.MediaBrowserConnection(getContext());
//        mMediaBrowserHelper.registerCallback(new PlayerFragment.MediaBrowserListener());
//
//
//        return view;
//    }
//
//    private void muteFunction() {
//        bnMute.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).withEndAction(() -> {
//            bnMute.setImageResource(R.drawable.icons8_mute_32);
//            bnMute.animate().rotation(360f).setDuration(300);
//            mMediaBrowserHelper.setVolume(0);
//            });
//    }
///** todo: yarden change
// * not in use
// * */
////    public void setProgressBarInvisible() {
////        pbLoading.setVisibility(View.GONE);
////    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mMediaBrowserHelper.onStart();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        sbVolumeSong.disconnectController();
//        mMediaBrowserHelper.onStop();
//    }
///** todo: yarden change
// * not in use
// * */
////    public void initTvTime(View view) {
////        //tvTime = view.findViewById(R.id.tvTime);
////    }
//
//    private void setupViews(View view) {
//        bnPlay = view.findViewById(R.id.button_play);
//        tvProgramName = view.findViewById(R.id.show_name);
//        tvTime = view.findViewById(R.id.show_number);
//        bnBack = view.findViewById(R.id.button_previous);
//        bnForward = view.findViewById(R.id.button_next);
//        sbVolumeSong = view.findViewById(R.id.seekbar);
//        bnMute = view.findViewById(R.id.button_mute);
//
//
//    }
//
//    /** todo: yarden change
//     * replace that method to all the setupView()
//     * */
////    public void initSbSong(View view) {
////      sbVolumeSong = view.findViewById(R.id.seekbar);
////    }
//
//    private void playFunction() {
//        if (!mIsPlaying) {
//            mMediaBrowserHelper.getTransportControls().play();
//            bnPlay.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).withEndAction(() -> {
//                bnPlay.setImageResource(R.drawable.icons8_pause_32);
//                bnPlay.animate().scaleX(1).scaleY(1).setDuration(200);
//            });
//        } else {
//            mMediaBrowserHelper.getTransportControls().pause();
//            bnPlay.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).withEndAction(() -> {
//                bnPlay.setImageResource(R.drawable.icons8_play_32);
//                bnPlay.animate().scaleX(1).scaleY(1).setDuration(200);
//            });
//        }
//    }
///** todo: yarden change
// * don't need that function we have play and resume
// * */
////    public void stopFunction() {
////        mMediaBrowserHelper.getTransportControls().stop();
////        bnPlay.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).withEndAction(() -> {
////            bnPlay.setImageResource(R.drawable.icons8_pause_32);
////        });
////    }
//
//    class MediaBrowserConnection extends MediaBrowserHelper {
//        private MediaBrowserConnection(Context context) {
//            super(context, MediaService.class);
//        }
//
//        @Override
//        protected void onConnected(@NonNull MediaControllerCompat mediaController) {
//            sbVolumeSong.setMediaController(mediaController);
////
////            MusicLibrary.createMediaMetadataCompat("f", "f", "f",
////                    "f", "f", 0,TimeUnit.SECONDS,
////                    "http://be.repoai.com:5080/WebRTCAppEE/streams/home/תוכנית_הכתבות_והראיונות_של_תלמידי_ביהס_עציון_בית_אשקטיין.mp4",
////                    R.drawable.ic_test1,"");
//        }
//
//        @Override
//        protected void onChildrenLoaded(@NonNull String parentId,
//                                        @NonNull List<MediaBrowserCompat.MediaItem> children) {
//            super.onChildrenLoaded(parentId, children);
//
//            final MediaControllerCompat mediaController = getMediaController();
//
//            // Queue up all media items for this simple sample.
//            for (final MediaBrowserCompat.MediaItem mediaItem : children) {
//                mediaController.addQueueItem(mediaItem.getDescription());
//            }
//
//            /** todo: yarden change
//             * disable that line because in the description says they not recommended
//             * to use that method and for now its work fine
//             * */
//            // Call prepare now so pressing play just works.
////            mediaController.getTransportControls().prepare();
//        }
//    }
//
//    /**
//     * Implementation of the {@link MediaControllerCompat.Callback} methods we're interested in.
//     * <p>
//     * Here would also be where one could override
//     * {@code onQueueChanged(List<MediaSessionCompat.QueueItem> queue)} to get informed when items
//     * are added or removed from the queue. We don't do this here in order to keep the UI
//     * simple.
//     */
//    class MediaBrowserListener extends MediaControllerCompat.Callback {
//        @Override
//        public void onPlaybackStateChanged(PlaybackStateCompat playbackState) {
//            mIsPlaying = playbackState != null &&
//                    playbackState.getState() == PlaybackStateCompat.STATE_PLAYING;
//            bnPlay.setPressed(mIsPlaying);
//        } //TODO: change
//
//        @Override
//        public void onMetadataChanged(MediaMetadataCompat mediaMetadata) {
//            if (mediaMetadata == null) {
//                return;
//            }
//            tvProgramName.setText(
//                    mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE));
//            tvProgramName.setSelected(true);
//            // tvStudentName.setText(
//            //         mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_ARTIST));
//            // tvStudentName.setSelected(true);
//        }
//
//        @Override
//        public void onSessionDestroyed() {
//            super.onSessionDestroyed();
//        }
//
//        @Override
//        public void onQueueChanged(List<MediaSessionCompat.QueueItem> queue) {
//            super.onQueueChanged(queue);
//        }
//    }
//}
