//package com.example.radiobe.database;
//import android.os.AsyncTask;
//import com.example.radiobe.StreamDAO;
//import com.example.radiobe.models.RadioItem;
//
//import java.util.List;
//
//public class DataBaseReader extends AsyncTask<Void,Void, List<RadioItem>> {
//
//    FinishedTask finishedTask;
//
//    public DataBaseReader(FinishedTask finishedTask){
//        this.finishedTask = finishedTask;
//    }
//
//
//    @Override
//    protected List<RadioItem> doInBackground(Void... voids) {
//        List<RadioItem> streams = StreamDAO.getInstance().readDataBase();
//        return streams;
//    }
//
//    @Override
//    protected void onPostExecute(List<RadioItem> radioItems) {          //observer
//        finishedTask.done(radioItems);
//    }
//}
//
//interface FinishedTask{
//    void done(List<RadioItem> streams);
//}