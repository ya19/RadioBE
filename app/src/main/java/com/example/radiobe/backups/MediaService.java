//package com.example.radiobe.radioLive;
//
//
//import android.app.Notification;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.media.MediaBrowserCompat;
//import android.support.v4.media.MediaDescriptionCompat;
//import android.support.v4.media.MediaMetadataCompat;
//import android.support.v4.media.session.MediaSessionCompat;
//import android.support.v4.media.session.PlaybackStateCompat;
//import android.util.Log;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import androidx.annotation.NonNull;
//import androidx.core.content.ContextCompat;
//import androidx.media.MediaBrowserServiceCompat;
//
////import com.example.android.mediasession.service.contentcatalogs.MusicLibrary;
////import com.example.android.mediasession.service.notifications.MediaNotificationManager;
////import com.example.android.mediasession.service.players.MediaPlayerAdapter;
//
//public class MediaService extends MediaBrowserServiceCompat {
//
//    private static final String TAG = MediaService.class.getSimpleName();
//
//    private MediaSessionCompat mSession;
//    private MediaPlayerAdapter mPlayback;
//    private MediaNotificationManager mMediaNotificationManager;
//    private MediaSessionCallback mCallback;
//    private boolean mServiceInStartedState;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        // Create a new MediaSession.
//        mSession = new MediaSessionCompat(this, "MediaService");
//        mCallback = new MediaSessionCallback();
//        mSession.setCallback(mCallback);
//        mSession.setFlags(
//                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
//                        MediaSessionCompat.FLAG_HANDLES_QUEUE_COMMANDS |
//                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
//        setSessionToken(mSession.getSessionToken());
//
//        mMediaNotificationManager = new MediaNotificationManager(this);
//
//        mPlayback = new MediaPlayerAdapter(this, new MediaPlayerListener());
//        Log.d(TAG, "onCreate: MusicService creating MediaSession, and MediaNotificationManager");
//    }
//
//    @Override
//    public void onTaskRemoved(Intent rootIntent) {
//        super.onTaskRemoved(rootIntent);
//        stopSelf();
//    }
//
//    @Override
//    public void onDestroy() {
//        mMediaNotificationManager.onDestroy();
//        mPlayback.stop();
//        mSession.release();
//        Log.d(TAG, "onDestroy: MediaPlayerAdapter stopped, and MediaSession released");
//    }
//
//    @Override
//    public BrowserRoot onGetRoot(@NonNull String clientPackageName,
//                                 int clientUid,
//                                 Bundle rootHints) {
//        return new BrowserRoot(MusicLibrary.getRoot(), null);
//    }
//
//    @Override
//    public void onLoadChildren(
//            @NonNull final String parentMediaId,
//            @NonNull final Result<List<MediaBrowserCompat.MediaItem>> result) {
//        result.sendResult(MusicLibrary.getMediaItems());
//
//        Log.d(TAG, "onDestroy: MediaPlayerAdapter stopped, and MediaSession released");
//    }
//
//    // MediaSession Callback: Transport Controls -> MediaPlayerAdapter
//    public class MediaSessionCallback extends MediaSessionCompat.Callback {
//        private final List<MediaSessionCompat.QueueItem> mPlaylist = new ArrayList<>();
//        private int mQueueIndex = -1;
//        private MediaMetadataCompat mPreparedMedia;
//
//        @Override
//        public void onAddQueueItem(MediaDescriptionCompat description) {
//            mPlaylist.add(new MediaSessionCompat.QueueItem(description, description.hashCode()));
//            mQueueIndex = (mQueueIndex == -1) ? 0 : mQueueIndex;
//            mSession.setQueue(mPlaylist);
//        }
//
//        @Override
//        public void onRemoveQueueItem(MediaDescriptionCompat description) {
//            mPlaylist.remove(new MediaSessionCompat.QueueItem(description, description.hashCode()));
//            mQueueIndex = (mPlaylist.isEmpty()) ? -1 : mQueueIndex;
//            mSession.setQueue(mPlaylist);
//        }
//
//        @Override
//        public void onPrepare() {
//            if (mQueueIndex < 0 && mPlaylist.isEmpty()) {
//                // Nothing to play.
//                return;
//            }
//
//            final String mediaId = mPlaylist.get(mQueueIndex).getDescription().getMediaId();
//            mPreparedMedia = MusicLibrary.getMetadata(MediaService.this, mediaId);
//            mSession.setMetadata(mPreparedMedia);
//
//            if (!mSession.isActive()) {
//                mSession.setActive(true);
//                Log.d(TAG, "session active");
//            }
//        }
//
//        @Override
//        public void onPlay() {
//            if (!isReadyToPlay()) {
//                // Nothing to play.
//                return;
//            }
//
//            if (mPreparedMedia == null) {
//                onPrepare();
//            }
//
//            mPlayback.playFromMedia(mPreparedMedia);
//            Log.d(TAG, "onPlayFromMediaId: MediaSession active");
//        }
//
//        @Override
//        public void onPause() {
//            mPlayback.pause();
//        }
//
//        @Override
//        public void onStop() {
//            mPlayback.stop();
//            mSession.setActive(false);
//        }
//
//        @Override
//        public void onSkipToNext() {
//            if (mPlaylist.size() == 0)
//                Toast.makeText(MediaService.this, "Size of PlayList is 0", Toast.LENGTH_LONG).show();
//            else {
//            mQueueIndex = (++mQueueIndex % mPlaylist.size());
//            mPreparedMedia = null;
//            onPlay();
//            }
//        }
//
//        @Override
//        public void onSkipToPrevious() {
//            mQueueIndex = mQueueIndex > 0 ? mQueueIndex - 1 : mPlaylist.size() - 1;
//            mPreparedMedia = null;
//            onPlay();
//        }
//
//        @Override
//        public void onSeekTo(long pos) {
//            mPlayback.seekTo(pos);
//        }
//
//        private boolean isReadyToPlay() {
//            return (!mPlaylist.isEmpty());
//        }
//    }
//
//    // MediaPlayerAdapter Callback: MediaPlayerAdapter state -> MusicService.
//    public class MediaPlayerListener {
//
//        private final ServiceManager mServiceManager;
//
//        MediaPlayerListener() {
//            mServiceManager = new ServiceManager();
//        }
//
//        public void onPlaybackStateChange(PlaybackStateCompat state) {
//            // Report the state to the MediaSession.
//            mSession.setPlaybackState(state);
//
//            // Manage the started state of this service.
//            switch (state.getState()) {
//                case PlaybackStateCompat.STATE_PLAYING:
//                    mServiceManager.moveServiceToStartedState(state);
//                    break;
//                case PlaybackStateCompat.STATE_PAUSED:
//                    mServiceManager.updateNotificationForPause(state);
//                    break;
//                case PlaybackStateCompat.STATE_STOPPED:
//                    mServiceManager.moveServiceOutOfStartedState(state);
//                    break;
//            }
//        }
//
//        //
//        public  void onPlaybackCompleted() {
//
//        }
//
//        class ServiceManager {
//
//            private void moveServiceToStartedState(PlaybackStateCompat state) {
//                Notification notification =
//                        mMediaNotificationManager.getNotification(
//                                mPlayback.getCurrentMedia(), state, getSessionToken());
//
//                if (!mServiceInStartedState) {
//                    ContextCompat.startForegroundService(
//                            MediaService.this,
//                            new Intent(MediaService.this, MediaService.class));
//                    mServiceInStartedState = true;
//                }
//
//                startForeground(MediaNotificationManager.NOTIFICATION_ID, notification);
//            }
//
//            private void updateNotificationForPause(PlaybackStateCompat state) {
//                stopForeground(false);
//                Notification notification =
//                        mMediaNotificationManager.getNotification(
//                                mPlayback.getCurrentMedia(), state, getSessionToken());
//                mMediaNotificationManager.getNotificationManager()
//                        .notify(MediaNotificationManager.NOTIFICATION_ID, notification);
//            }
//
//            private void moveServiceOutOfStartedState(PlaybackStateCompat state) {
//                stopForeground(true);
//                stopSelf();
//                mServiceInStartedState = false;
//            }
//        }
//
//    }
//
//}