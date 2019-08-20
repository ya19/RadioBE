//package com.example.radiobe;
//
//
//
//import android.text.format.DateFormat;
//
//import com.cloudant.client.api.Database;
//import com.example.radiobe.models.RadioItem;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import com.cloudant.client.api.ClientBuilder;
//import com.cloudant.client.api.CloudantClient;
//import com.example.radiobe.models.User;
//import com.google.firebase.auth.FirebaseUser;
//
//
////1) get streamsQuery
////2) write to database
////3) update database/
////4) delete database for favorites
////5) read from database
////6) write new data to database
//
//public class StreamDAO2 {
//    private final String API_KEY = "wooducessideresedlyinchi";
//    private final String API_PASS = "7ac241ffc5b2876163373ff4f273570a3191a184";
//    private final String DB_USER_NAME = "2115411f-0004-4f8b-93f0-0e70c716455b-bluemix";
//    private final String DB_STREAMS = "streams";
//    private final String DB_USERS = "users";
//
//
//    //new api for users data base.
////    Key:ctsestteakethistargellds
////    Password:645fc1fe96fb6252dc1da4bb5601c1e7768041f7
//
//
//    private final String usersQuery = "{\n" +
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
//
//    private static StreamDAO instance;
//    CloudantClient client;
//    //    private static com.cloudant.client.api.Database streamsDataBase;
//    private Database streamsDataBase;
//    private Database usersDataBase;
//
//    private List<RadioItem> streams = new ArrayList<>();
//
//    public static StreamDAO getInstance() {
//        //lazy loading (only loaded when the database is first used) unlike the auto singleton here...
//        if (instance == null)
//            instance = new StreamDAO();
//
//        return instance;
//    }
//
//    private StreamDAO() {
////        accessDb();
//    }
//
//
//    //TODO: read streamsQuery and check if exits in database, if not add.
//    public void parseJson() {
//        String dataBaseLink = "http://be.repoai.com:5080/WebRTCAppEE/rest/broadcast/getVodList/0/100?fbclid=IwAR2rtbhZkchHYZ3rbREpP0gaqNVPkvNn7phnQthgM4OO1gl5elu5zZjlQrk";
//        try {
//            URL url = new URL(dataBaseLink);
//            URLConnection con = url.openConnection();
//
//            InputStream in = con.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            StringBuilder builder = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                builder.append(line);
//            }
//
//            String json = builder.toString();
//
//            try {
//                JSONArray resultsArray = new JSONArray(json);
//
//
//                for (int i = 0; i < resultsArray.length(); i++) {
//                    JSONObject radioItem = resultsArray.getJSONObject(i);
//
//                    String vodName = radioItem.getString("vodName");
//                    String change = vodName.replace('_', ' ');
//                    String itemName = change.replace(".mp4", ""); //todo replace .mp4 to ' '
//                    long creationDate = radioItem.getLong("creationDate");
//
//                    String creationDateString = DateFormat.format("dd/MM/yyyy", new Date(creationDate)).toString();
//
//
//                    long duration = radioItem.getInt("duration");
//                    String filePath = "http://be.repoai.com:5080/WebRTCAppEE/streams/home/" + vodName;              //todo: notice that i changed the duration to long if there's any problem
//
//                    RadioItem item = new RadioItem(duration, vodName, itemName, creationDate , creationDateString, filePath);
//                    //            streams.add(item);
//
//                    streams.add(item);
////                    new DatabaseUpdater(DatabaseUpdater.SAVE_STREAM, item).execute();
////                    saveToDataBase(item); //todo check how to save outside the FOR loop
//                }
//
//                saveItemsToDataBase(streams);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private void saveItemsToDataBase(List<RadioItem> jsonStreams) {
//        List<RadioItem> items = readDataBase();
//        for (RadioItem jsonItem : jsonStreams) {
//            for (RadioItem item : items) {
//                if(jsonItem.getVodName().equals(item.getVodName()))
//                    return;
//
//            }
//            streamsDataBase.save(jsonItem);
//        }
//
//    }
//
//    public void accessDb() {
//        client = ClientBuilder.account(DB_USER_NAME)
//                .username(API_KEY)
//                .password(API_PASS)
//                .build();
//        streamsDataBase = client.database(DB_STREAMS, false);
//        usersDataBase = client.database(DB_USERS, false);
//    }
//
////
////    public void saveToDataBase(RadioItem radioItem) {
////        List<RadioItem> items = readDataBase();
//////        List<RadioItem> items22 = new DataBaseReader().execute().get();   consider!
////        for (RadioItem item : items) {
////            if (radioItem.getVodName().equals(item.getVodName()))
////                return;
////        }
////        //TODO check if the streamsQuery object exits in the database
////        streamsDataBase.save(radioItem);
////    }
//
//    public List<RadioItem> readDataBase() {
//        String streamsQuery = "{\n" +
//                "   \"selector\": {\n" +
//                "      \"_id\": {\n" +
//                "         \"$gt\": \"0\"\n" +
//                "      }\n" +
//                "   },\n" +
//                "   \"fields\": [\n" +
//                "      \"_id\",\n" +
//                "      \"_rev\"\n" +
//                "   ],\n" +
//                "   \"sort\": [\n" +
//                "      {\n" +
//                "         \"_id\": \"asc\"\n" +
//                "      }\n" +
//                "   ]\n" +
//                "}";
//        List<RadioItem> radioItems = streamsDataBase.findByIndex(streamsQuery, RadioItem.class);
//        System.out.println("Data was read");
//
//        return radioItems;
//    }
//
//    public List<User> readUsersDataBase() {
//        List<User> users = usersDataBase.findByIndex(usersQuery, User.class);
//        System.out.println("Users Data was read");
//
//        return users;
//    }
//
//
//
//
//    public void addLikesDB(RadioItem radioItem, FirebaseUser firebaseUser) {
//
//        if(firebaseUser!= null) {
//
//            User user = new User(firebaseUser.getEmail());
//            List<RadioItem> streams = readDataBase();
//            for (int i = 0; i < streams.size(); i++) {
//                RadioItem item = streams.get(i);
//
//                if (radioItem.get_id().equals(item.get_id())) {
//                    List<User> streamLikes = item.getUsersThatLiked();
//                    for (User streamLikeUser : streamLikes) {
//                        if (streamLikeUser.getEmail().equals(user.getEmail())) {
//                            System.out.println("Data can't be liked!");
//                            return;
//                        }
//                    }
//
//                    //if the stream doesn't have this user to like him, set likes and update database.
//                    item.addLikeUser(user);
//                    item.setLikes(item.getLikes() + 1);
//                    streamsDataBase.update(item);
//                    System.out.println("Data Liked!");
//                }
//            }
////                streamsDataBase.remove(item);
//
//        } //new RadioItemsDataSource().execute(); //TODO : moved this to onPostExecute of DatabaseUpdater.
//    }
//
//
//
//    public void saveUserToDataBase(User newUser) {
//        List<User> users = usersDataBase.findByIndex(usersQuery, User.class);
//        for (User user : users) {
//            if (newUser.getEmail().equals(user.getEmail()))
//                return;
//
//        }
//        usersDataBase.save(newUser);
//
//    }
//
//}
//
//
