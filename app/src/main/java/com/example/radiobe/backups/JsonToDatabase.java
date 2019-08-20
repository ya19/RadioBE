//package com.example.radiobe.database;
//
//import android.os.AsyncTask;
//
//import com.example.radiobe.StreamDAO;
//
//
//public class JsonToDatabase extends AsyncTask<Void, Void, Void> {
//
//    JsonToDatabaseListener listener;
//
//    public JsonToDatabase(JsonToDatabaseListener listener) {
//        this.listener = listener;
//    }
//
//    @Override
//    protected Void doInBackground(Void... voids) {
//
//        StreamDAO.getInstance().accessDb();
//        StreamDAO.getInstance().parseJson();
//
//        return null;
//    }
//
//
//    @Override
//    protected void onPostExecute(Void aVoid) {
//        listener.done();
//    }
//}
//
//
