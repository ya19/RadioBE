package com.example.radiobe.database;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiobe.StreamDAO;
import com.example.radiobe.adapters.RadioItemsAdapter;
import com.example.radiobe.models.RadioItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FirebaseItemsDataSource{

    private WeakReference<RecyclerView> rvRadioItems;
    private WeakReference<ProgressBar> progressBar;
    List<RadioItem> fireBaseStreams;
    List<Boolean> isDoneAll = new ArrayList<>();
    Timer t;
    public RadioItemsAdapter adapter;
    private static FirebaseItemsDataSource instance;

    public static FirebaseItemsDataSource getInstance() {
        if (instance == null)
            instance = new FirebaseItemsDataSource();

//        else
//            instance.listener = null;

        return instance;
    }


    private FirebaseItemsDataSource(){
        t = new Timer();
    }

//    public FirebaseItemsDataSource(RecyclerView rvRadioItems, ProgressBar progressBar, List<RadioItem> fireBaseStreams) {
//        this.rvRadioItems = new WeakReference<>(rvRadioItems);
//        this.progressBar = new WeakReference<>(progressBar);
//        this.fireBaseStreams = fireBaseStreams;
//        this.loadData();
//        t = new Timer();
//    }


//    public void initGeneral(RecyclerView rvRadioItems, ProgressBar progressBar){
//        this.rvRadioItems = new WeakReference<>(rvRadioItems);
//        this.progressBar = new WeakReference<>(progressBar);
//        this.loadData();
//        t = new Timer();
//    }

    public void setStreams(List<RadioItem> jsonStreams){
        fireBaseStreams = jsonStreams;
    }

    public List<RadioItem> getFireBaseStreams(){
        return this.fireBaseStreams;
    }

    public void loadData(DoneUpdatingLikes doneUpdatingLikes){
        //boolean once = true;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        if (fireBaseStreams.size() > 0) {
            for (int i = 0; i < fireBaseStreams.size(); i++) {
                int finalI = i;
                ref.child("likes").child("id").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        fireBaseStreams.get(finalI).setLikes(dataSnapshot.getChildrenCount());
                        isDoneAll.add(true);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //if can't read, no likes
                        fireBaseStreams.get(finalI).setLikes(0);
                        isDoneAll.add(true);
                    }
                });
            }

            t.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    checkLoadingComplete(doneUpdatingLikes);
                }
            }, 0 , 250);
        }







    }

    public void checkLoadingComplete(DoneUpdatingLikes doneUpdatingLikes){
        if (isDoneAll.size() == fireBaseStreams.size()) {

            //stop timer
            t.cancel();
            t.purge();

            doneUpdatingLikes.done();


//
//            //call listener and deliver streams
//            //load the all programs (adapter shit)
//            RecyclerView recycler = this.rvRadioItems.get(); //check if what rvSongs is pointing on , is not null
//            ProgressBar pb = this.progressBar.get();
//
//
//            if (pb == null) return;
//            pb.setVisibility(View.GONE);
//
//            if (recycler == null) return;
//
////            Context context = recycler.getContext();
////            adapter = new RadioItemsAdapter(fireBaseStreams, recycler, pb, context);
////            recycler.setAdapter(adapter);
//
////            recycler.setLayoutManager(new LinearLayoutManager(context));//may change to a different manager (grid, staggered..)
//            System.out.println("Refresh");
//            //once = false;

        }
    }

    }

    interface DoneUpdatingLikes{
    void done();
        }
