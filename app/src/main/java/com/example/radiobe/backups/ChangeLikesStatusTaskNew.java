//package com.example.radiobe.backups;
//
//import android.os.AsyncTask;
//
//import com.cloudant.client.api.ClientBuilder;
//import com.cloudant.client.api.CloudantClient;
//import com.cloudant.client.api.Database;
//import com.example.radiobe.models.RadioItem;
//import com.example.radiobe.models.User;
//import com.google.firebase.auth.FirebaseUser;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ChangeLikesStatusTaskNew extends AsyncTask<Void, Void, Void> {
//
//    private boolean isLiked;
//    private boolean newUserInitialized = false;
//    private FirebaseUser firebaseUser;
//    private RadioItem radioItem;
//
//    private final String API_KEY = "wooducessideresedlyinchi";
//    private final String API_PASS = "7ac241ffc5b2876163373ff4f273570a3191a184";
//    private final String DB_USER_NAME = "2115411f-0004-4f8b-93f0-0e70c716455b-bluemix";
//    private final String DB_STREAMS = "streams";
//    private final String DB_USERS = "users";
//
//
//        boolean addLike;
//    private User newUser;
//
//    private CloudantClient client;
//    private Database streamsDataBase;
//    private Database usersDataBase;
//    private String query = "{\n" +
//            "   \"selector\": {\n" +
//            "      \"_id\": {\n" +
//            "         \"$gt\": \"0\"\n" +
//            "      }\n" +
//            "   },\n" +
//            "   \"fields\": [\n" +
//            "      \"_id\",\n" +
//            "      \"_rev\"\n" +
//            "   ],\n" +
//            "   \"sort\": [\n" +
//            "      {\n" +
//            "         \"_id\": \"asc\"\n" +
//            "      }\n" +
//            "   ]\n" +
//            "}";
//
//    public ChangeLikesStatusTaskNew(FirebaseUser firebaseUser, RadioItem radioItem, boolean isLiked) {
//        this.firebaseUser = firebaseUser;
//        this.radioItem = radioItem;
//        this.isLiked = isLiked;
//    }
//
//    @Override
//    protected Void doInBackground(Void... voids) {
//        accessDb();
////
////        if (firebaseUser != null) {
//
////            User user = new User(firebaseUser.getEmail());
//            List<RadioItem> streams = streamsDataBase.findByIndex(query, RadioItem.class);
//            List<User> users = usersDataBase.findByIndex(query, User.class);
//
//
//            for (User user : users) {
//                if (firebaseUser.getEmail().equals(user.getEmail())) {
//                    newUser = user;
//                    newUserInitialized = true;
//                }
//            }
//
//            if(!newUserInitialized){
//                newUser = new User(firebaseUser.getEmail());
//            }
//
//
//            for (RadioItem item : streams) {
//                if (radioItem.get_id().equals(item.get_id())) {
//                    List<User> streamLikes = radioItem.getUsersThatLiked();
//
//                    if(streamLikes.contains(newUser)){
//                        radioItem.setLikes(radioItem.getLikes() - 1);
//
//                        streamLikes.remove(newUser);
//                        List<User> david = new ArrayList<>();
//                        radioItem.setUsersThatLiked(david);
////                        radioItem.removeLike(newUser);
//                        streamsDataBase.update(radioItem);
//                        System.out.println("returning!");
//                        return null;
//                    }
//
//                    radioItem.setLikes(radioItem.getLikes() + 1);
//                    radioItem.addLike(newUser);
//                    System.out.println("Data liked");
//                    streamsDataBase.update(radioItem);
//                    return null;
//
//
//
//
///*                    for (User streamLikeUser : streamLikes) {
//                        if (streamLikeUser.getEmail().equals(newUser.getEmail())) {
//                            radioItem.setLikes(radioItem.getLikes() - 1);
//                            streamLikes.add(newUser);
//                            radioItem.setUsersThatLiked(streamLikes);
////                            addLike = false;
//////                            radioItem.changeLikeStatus(newUser, addLike);
////                            radioItem.removeLike(newUser);
//                            streamsDataBase.update(radioItem);
//                            System.out.println("About to return!");
//                            return null;
//                        }
//                    }
//                    addLike = true;
//                    //if the stream doesn't have this user to like him, set likes and update database.
////                    item.changeLikeStatus(newUser, addLike);
//                    radioItem.addLike(newUser);
//                    radioItem.setLikes(item.getLikes() + 1);
//                    streamsDataBase.update(radioItem);
//                    System.out.println("Data Liked!");
//                    return null;*/
//                }
//            }
//                                                                                                        //                streamsDataBase.remove(item);
//
//
//                                                                                                        //        }
//        return null;
//    }
//
//    private void accessDb() {
//        client = ClientBuilder.account(DB_USER_NAME)
//                .username(API_KEY)
//                .password(API_PASS)
//                .build();
//        streamsDataBase = client.database(DB_STREAMS, false);
//        usersDataBase = client.database(DB_USERS, false);
//    }
//}
//
