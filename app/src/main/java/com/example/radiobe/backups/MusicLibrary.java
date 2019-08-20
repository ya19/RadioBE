//package com.example.radiobe.radioLive;
//
//
//import android.content.ContentResolver;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.support.v4.media.MediaBrowserCompat;
//import android.support.v4.media.MediaMetadataCompat;
//
//import com.example.radiobe.BuildConfig;
//import com.example.radiobe.models.RadioItem;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.TreeMap;
//import java.util.concurrent.TimeUnit;
//
///*import com.example.android.mediasession.BuildConfig;
//import com.example.android.mediasession.R;*/
//
//
//public class MusicLibrary {
//
//    private static final TreeMap<String, MediaMetadataCompat> music = new TreeMap<>();
//    private static final HashMap<String, Integer> albumRes = new HashMap<>();
//    private static final HashMap<String, String> musicFileName = new HashMap<>();
//
////    static {
////        createMediaMetadataCompat(
////                "Jazz_In_Paris",
////                "Jazz in Paris",
////                "Media Right Productions",
////                "Jazz & Blues",
////                "Jazz",
////                103,
////                TimeUnit.SECONDS,
////                "jazz_in_paris.mp3",
////                R.drawable.ic_test1,
////                "album_jazz_blues");
////        createMediaMetadataCompat(
////                "The_Coldest_Shoulder",
////                "The Coldest Shoulder",
////                "The 126ers",
////                "Youtube Audio Library Rock 2",
////                "Rock",
////                160,
////                TimeUnit.SECONDS,
////                "the_coldest_shoulder.mp3",
////                R.drawable.ic_test1,
////                "album_youtube_audio_library_rock_2");
////    }
//
//    public static void playFromFragment(RadioItem item){
////        MediaPlayerAdapter.releasePlayer();
//        createMediaMetadataCompat(
//                        item.get_id(),
//                        item.getItemName(),
//                        "David",
//                        "No Album",
//                        "no genre",
//                        item.getDuration(),
//                        TimeUnit.SECONDS,
//                        item.getFilePath(),
//                        item.getResImage(),
//                "No Album"
//        );
//
//    }
//
//    public static String getRoot() {
//        return "root";
//    }
//
//    private static String getAlbumArtUri(String albumArtResName) {
//        return ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
//                BuildConfig.APPLICATION_ID + "/drawable/" + albumArtResName;
//    }
//
//    public static String getMusicFilename(String mediaId) {
//        System.out.print(musicFileName.get(mediaId));
//        return musicFileName.containsKey(mediaId) ? musicFileName.get(mediaId) : null;
//    }
//
//    private static int getAlbumRes(String mediaId) {
//        return albumRes.containsKey(mediaId) ? albumRes.get(mediaId) : 0;
//    }
//
//    public static Bitmap getAlbumBitmap(Context context, String mediaId) {
//        return BitmapFactory.decodeResource(context.getResources(),
//                MusicLibrary.getAlbumRes(mediaId));
//    }
//
//    public static List<MediaBrowserCompat.MediaItem> getMediaItems() {
//        List<MediaBrowserCompat.MediaItem> result = new ArrayList<>();
//        for (MediaMetadataCompat metadata : music.values()) {
//            result.add(
//                    new MediaBrowserCompat.MediaItem(
//                            metadata.getDescription(), MediaBrowserCompat.MediaItem.FLAG_PLAYABLE));
//        }
//        return result;
//    }
//
//    public static MediaMetadataCompat getMetadata(Context context, String mediaId) {
//        MediaMetadataCompat metadataWithoutBitmap = music.get(mediaId);
//        Bitmap albumArt = getAlbumBitmap(context, mediaId);
//
//        // Since MediaMetadataCompat is immutable, we need to create a copy to set the album art.
//        // We don't set it initially on all items so that they don't take unnecessary memory.
//        MediaMetadataCompat.Builder builder = new MediaMetadataCompat.Builder();
//        for (String key :
//                new String[]{
//                        MediaMetadataCompat.METADATA_KEY_MEDIA_ID,
//                        MediaMetadataCompat.METADATA_KEY_ALBUM,
//                        MediaMetadataCompat.METADATA_KEY_ARTIST,
//                        MediaMetadataCompat.METADATA_KEY_TITLE
//                }) {
//            builder.putString(key, metadataWithoutBitmap.getString(key));
//        }
//        builder.putLong(
//                MediaMetadataCompat.METADATA_KEY_DURATION,
//                metadataWithoutBitmap.getLong(MediaMetadataCompat.METADATA_KEY_DURATION));
//        builder.putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, albumArt);
//        return builder.build();
//    }
//
//    public static void createMediaMetadataCompat(
//            String mediaId,
//            String title,
//            String artist,
//            String album,
//            String genre,
//            long duration,
//            TimeUnit durationUnit,
//            String musicFilename,
//            int albumArtResId,
//            String albumArtResName) {
//        music.put(
//                mediaId,
//                new MediaMetadataCompat.Builder()
//                        .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, mediaId)
//                        .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, album)
//                        .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, artist)
//                        .putLong(MediaMetadataCompat.METADATA_KEY_DURATION,
//                                TimeUnit.MILLISECONDS.convert(duration, durationUnit))
//                        .putString(MediaMetadataCompat.METADATA_KEY_GENRE, genre)
//                        .putString(
//                                MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI,
//                                getAlbumArtUri(albumArtResName))
//                        .putString(
//                                MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI,
//                                getAlbumArtUri(albumArtResName))
//                        .putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
//                        .build());
//        albumRes.put(mediaId, albumArtResId);
//        musicFileName.put(mediaId, musicFilename);
//    }
//}