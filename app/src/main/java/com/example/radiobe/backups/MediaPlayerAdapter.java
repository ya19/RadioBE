//package com.example.radiobe.radioLive;
//
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.res.AssetFileDescriptor;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.os.SystemClock;
//import android.support.v4.media.MediaMetadataCompat;
//import android.support.v4.media.session.PlaybackStateCompat;
//import android.util.Log;
//
//import java.io.IOException;
//
//import androidx.annotation.NonNull;
//
///**
// * Abstract player implementation that handles playing music with proper handling of headphones
// * and audio focus.
// */
//public class MediaPlayerAdapter {
//
//    private static final float MEDIA_VOLUME_DEFAULT = 1.0f;
//    private static final float MEDIA_VOLUME_DUCK = 0.2f;
//
//    private static final IntentFilter AUDIO_NOISY_INTENT_FILTER =
//            new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
//
//    private boolean mAudioNoisyReceiverRegistered = false;
//    private final BroadcastReceiver mAudioNoisyReceiver =
//            new BroadcastReceiver() {
//                @Override
//                public void onReceive(Context context, Intent intent) {
//                    if (AudioManager.ACTION_AUDIO_BECOMING_NOISY.equals(intent.getAction())) {
//                        if (isPlaying()) {
//                            pause();
//                        }
//                    }
//                }
//            };
//
//    private final AudioManager mAudioManager;
//    private final AudioFocusHelper mAudioFocusHelper;
//
//    private boolean mPlayOnAudioFocus = false;
//
//    private final Context mContext;
//    private static MediaPlayer mMediaPlayer;
//    private String mFilename;
//    private MediaService.MediaPlayerListener mPlaybackInfoListener; //originally extends "playbckinfolistener independent class"
//    private MediaMetadataCompat mCurrentMedia;
//    private int mState;
//    private boolean mCurrentMediaPlayedToCompletion;
//
//    // Work-around for a MediaPlayer bug related to the behavior of MediaPlayer.seekTo()
//    // while not playing.
//    private int mSeekWhileNotPlaying = -1;
//
//    public MediaPlayerAdapter(@NonNull Context context, MediaService.MediaPlayerListener listener) {
//        mContext = context.getApplicationContext();
//        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
//        mAudioFocusHelper = new AudioFocusHelper();
//
//        mPlaybackInfoListener = listener;
//    }
//
//    private void initializeMediaPlayer() {
//        if (mMediaPlayer == null) {
//            mMediaPlayer = new MediaPlayer();
//            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mediaPlayer) {
//                    mPlaybackInfoListener.onPlaybackCompleted();
//
//                    // Set the state to "paused" because it most closely matches the state
//                    // in MediaPlayer with regards to available state transitions compared
//                    // to "stop".
//                    // Paused allows: seekTo(), start(), pause(), stop()
//                    // Stop allows: stop()
//                    setNewState(PlaybackStateCompat.STATE_PAUSED);
//                }
//            });
//        }
//    }
//
//    public void playFromMedia(MediaMetadataCompat metadata) {
//        mCurrentMedia = metadata;
//        final String mediaId = metadata.getDescription().getMediaId();
//        playFile(MusicLibrary.getMusicFilename(mediaId));
//    }
//
//    public MediaMetadataCompat getCurrentMedia() {
//        return mCurrentMedia;
//    }
//
//    public boolean isPlaying() {
//        return mMediaPlayer != null && mMediaPlayer.isPlaying();
//    }
//
//    public static void releasePlayer(){
//        mMediaPlayer.release();
//    }
//
//    private void playFile(String filename) {
////        String url = radioItem.getFilePath();
//        boolean mediaChanged = (mFilename == null || !filename.equals(mFilename));
//        if (mCurrentMediaPlayedToCompletion) {
//            // Last audio file was played to completion, the resourceId hasn't changed, but the
//            // player was released, so force a reload of the media file for playback.
//            mediaChanged = true;
//            mCurrentMediaPlayedToCompletion = false;
//        }
//        if (!mediaChanged) {
//            if (!isPlaying()) {
//                play();
//            }
//            return;
//        } else {
//            release();
//        }
//
//        mFilename = filename;
//
//        initializeMediaPlayer();
//
//        try {
//            mMediaPlayer.setDataSource(filename);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
////        try {
////            Log.d("helo there,", "all good");
////            AssetFileDescriptor assetFileDescriptor = mContext.getAssets().openFd(mFilename);
////            mMediaPlayer.setDataSource(
////                    assetFileDescriptor.getFileDescriptor(),
////                    assetFileDescriptor.getStartOffset(),
////                    assetFileDescriptor.getLength());
////        } catch (IOException e) {
////              throw new RuntimeException("Failed to open file: " + mFilename, e);
////        }
//
//        try {
//            mMediaPlayer.prepareAsync();
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to open file: " + mFilename, e);
//        }
//
//        play();
//    }
//
//    public final void play() {
//        if (mAudioFocusHelper.requestAudioFocus()) {
//            registerAudioNoisyReceiver();
//            onPlay();
//        }
//    }
//
//    /**
//     * Called when media is ready to be played and indicates the app has audio focus.
//     */
//    protected void onPlay() {
//        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
//            mMediaPlayer.start();
//            setNewState(PlaybackStateCompat.STATE_PLAYING);
//        }
//    }
//
//    public final void pause() {
//        if (!mPlayOnAudioFocus) {
//            mAudioFocusHelper.abandonAudioFocus();
//        }
//
//        unregisterAudioNoisyReceiver();
//        onPause();
//    }
//
//    /**
//     * Called when media must be paused.
//     */
//    protected void onPause() {
//        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
//            mMediaPlayer.pause();
//            setNewState(PlaybackStateCompat.STATE_PAUSED);
//        }
//    }
//
//    public final void stop() {
//        mAudioFocusHelper.abandonAudioFocus();
//        unregisterAudioNoisyReceiver();
//        onStop();
//    }
//
//    /**
//     * Called when the media must be stopped. The player should clean up resources at this
//     * point.
//     */
//    public void onStop() {
//        // Regardless of whether or not the MediaPlayer has been created / started, the state must
//        // be updated, so that MediaNotificationManager can take down the notification.
//        setNewState(PlaybackStateCompat.STATE_STOPPED);
//        release();
//    }
//
//    private void release() {
//        if (mMediaPlayer != null) {
//            mMediaPlayer.release();
//            mMediaPlayer = null;
//        }
//    }
//
//    // This is the main reducer for the player state machine.
//    private void setNewState(@PlaybackStateCompat.State int newPlayerState) {
//        mState = newPlayerState;
//
//        // Whether playback goes to completion, or whether it is stopped, the
//        // mCurrentMediaPlayedToCompletion is set to true.
//        if (mState == PlaybackStateCompat.STATE_STOPPED) {
//            mCurrentMediaPlayedToCompletion = true;
//        }
//
//        // Work around for MediaPlayer.getCurrentPosition() when it changes while not playing.
//        final long reportPosition;
//        if (mSeekWhileNotPlaying >= 0) {
//            reportPosition = mSeekWhileNotPlaying;
//
//            if (mState == PlaybackStateCompat.STATE_PLAYING) {
//                mSeekWhileNotPlaying = -1;
//            }
//        } else {
//            reportPosition = mMediaPlayer == null ? 0 : mMediaPlayer.getCurrentPosition();
//        }
//
//        final PlaybackStateCompat.Builder stateBuilder = new PlaybackStateCompat.Builder();
//        stateBuilder.setActions(getAvailableActions());
//        stateBuilder.setState(mState,
//                reportPosition,
//                1.0f,
//                SystemClock.elapsedRealtime());
//        mPlaybackInfoListener.onPlaybackStateChange(stateBuilder.build());
//    }
//
//    /**
//     * Set the current capabilities available on this session. Note: If a capability is not
//     * listed in the bitmask of capabilities then the MediaSession will not handle it. For
//     * example, if you don't want ACTION_STOP to be handled by the MediaSession, then don't
//     * included it in the bitmask that's returned.
//     */
//    @PlaybackStateCompat.Actions
//    private long getAvailableActions() {
//        long actions = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID
//                | PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH
//                | PlaybackStateCompat.ACTION_SKIP_TO_NEXT
//                | PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS;
//        switch (mState) {
//            case PlaybackStateCompat.STATE_STOPPED:
//                actions |= PlaybackStateCompat.ACTION_PLAY
//                        | PlaybackStateCompat.ACTION_PAUSE;
//                break;
//            case PlaybackStateCompat.STATE_PLAYING:
//                actions |= PlaybackStateCompat.ACTION_STOP
//                        | PlaybackStateCompat.ACTION_PAUSE
//                        | PlaybackStateCompat.ACTION_SEEK_TO;
//                break;
//            case PlaybackStateCompat.STATE_PAUSED:
//                actions |= PlaybackStateCompat.ACTION_PLAY
//                        | PlaybackStateCompat.ACTION_STOP;
//                break;
//            default:
//                actions |= PlaybackStateCompat.ACTION_PLAY
//                        | PlaybackStateCompat.ACTION_PLAY_PAUSE
//                        | PlaybackStateCompat.ACTION_STOP
//                        | PlaybackStateCompat.ACTION_PAUSE;
//        }
//        return actions;
//    }
//
//    public void seekTo(long position) {
//        if (mMediaPlayer != null) {
//            if (!mMediaPlayer.isPlaying()) {
//                mSeekWhileNotPlaying = (int) position;
//            }
//            mMediaPlayer.seekTo((int) position);
//
//            // Set the state (to the current state) because the position changed and should
//            // be reported to clients.
//            setNewState(mState);
//        }
//    }
//
//    public void setVolume(float volume) {
//        if (mMediaPlayer != null) {
//            mMediaPlayer.setVolume(volume, volume);
//        }
//    }
//
//    private void registerAudioNoisyReceiver() {
//        if (!mAudioNoisyReceiverRegistered) {
//            mContext.registerReceiver(mAudioNoisyReceiver, AUDIO_NOISY_INTENT_FILTER);
//            mAudioNoisyReceiverRegistered = true;
//        }
//    }
//
//    private void unregisterAudioNoisyReceiver() {
//        if (mAudioNoisyReceiverRegistered) {
//            mContext.unregisterReceiver(mAudioNoisyReceiver);
//            mAudioNoisyReceiverRegistered = false;
//        }
//    }
//
//    /**
//     * Helper class for managing audio focus related tasks.
//     */
//    private final class AudioFocusHelper
//            implements AudioManager.OnAudioFocusChangeListener {
//
//        private boolean requestAudioFocus() {
//            final int result = mAudioManager.requestAudioFocus(this,
//                    AudioManager.STREAM_MUSIC,
//                    AudioManager.AUDIOFOCUS_GAIN);
//            return result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
//        }
//
//        private void abandonAudioFocus() {
//            mAudioManager.abandonAudioFocus(this);
//        }
//
//        @Override
//        public void onAudioFocusChange(int focusChange) {
//            switch (focusChange) {
//                case AudioManager.AUDIOFOCUS_GAIN:
//                    if (mPlayOnAudioFocus && !isPlaying()) {
//                        play();
//                    } else if (isPlaying()) {
//                        setVolume(MEDIA_VOLUME_DEFAULT);
//                    }
//                    mPlayOnAudioFocus = false;
//                    break;
//                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
//                    setVolume(MEDIA_VOLUME_DUCK);
//                    break;
//                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
//                    if (isPlaying()) {
//                        mPlayOnAudioFocus = true;
//                        pause();
//                    }
//                    break;
//                case AudioManager.AUDIOFOCUS_LOSS:
//                    mAudioManager.abandonAudioFocus(this);
//                    mPlayOnAudioFocus = false;
//                    stop();
//                    break;
//            }
//        }
//    }
//}