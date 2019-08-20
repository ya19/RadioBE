//package com.example.radiobe.database;
//
//import android.os.AsyncTask;
//
//import com.example.radiobe.StreamDAO;
//import com.example.radiobe.models.RadioItem;
//import com.example.radiobe.models.User;
//import com.google.firebase.auth.FirebaseUser;
//
//public class DatabaseUpdater extends AsyncTask<Void, Void, Void> {
//    public static final int ADD_LIKES = 1;
//    public static final int ADD_VIEWS = 2;
//    public static final int ADD_COMMENT = 3;
//    public static final int SAVE_STREAM = 4;
//    public static final int SAVE_USER = 5;
//    public static final int SAVE_JSON = 6;
//
//    private int action;
//    private RadioItem item;
//    private User user;
//    FirebaseUser firebaseUser;
//
////    private WeakReference<RecyclerView> rvRadioItems;
////    private WeakReference<ProgressBar> progressBar;
//
//    //ctor for recycler view and progress bar after refresh.
////    public DatabaseUpdater(int action, RadioItem item, RecyclerView rv, ProgressBar pb) {
////        this.action = action;
////        this.item = item;
////        this.rvRadioItems = new WeakReference<>(rv);
////        this.progressBar = new WeakReference<>(pb);
////
////    }
//
//    public DatabaseUpdater(int action, RadioItem item, FirebaseUser firebaseUser) {
//        this.action = action;
//        this.item = item;
//        this.firebaseUser = firebaseUser;
//
//    }
//
//
//    //ctor for any actions relevant to streams database
//    public DatabaseUpdater(int action, RadioItem item) {
//        this.action = action;
//        this.item = item;
//
//    }
//
//    //ctor for actions relevant to users database
//    public DatabaseUpdater(int action, User user) {
//        this.action = action;
//        this.user = user;
//    }
//
//    @Override
//    protected Void doInBackground(Void... voids) {
//        switch (action) {
//            case 1:
//                StreamDAO.getInstance().addLikesDB(item, firebaseUser);
//                break;
//
////            case 4:
////                StreamDAO.getInstance().saveToDataBase(item);
////                break;      //TODO: maybe seperate to Users and streams database updaters.
//
//            case 5:
//                StreamDAO.getInstance().saveUserToDataBase(user);
//                break;
//
//        }
//
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Void aVoid) {
//
////
////        switch (action){
////            case 5:
////                //TODO: something with the users.
////
////
////                //TODO: Check if maybe delete this part cause adding a like scrolls you up to the top of the recycler
////            default:
////                RecyclerView recycler = this.rvRadioItems.get(); //check if what rvSongs is pointing on , is not null
////                ProgressBar pb = this.progressBar.get();
////
////
////                if (pb == null) return;
////                pb.setVisibility(View.GONE);
////
////                if (recycler == null) return;
////                new RadioItemsDataSource(recycler , pb).execute();
////
////        }
//
//    }
//
//}
