package com.example.radiobe.database;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class FirebaseItemsDataSource extends AsyncTask<Void,Void,Void> {

    private WeakReference<RecyclerView> rvRadioItems;
    private WeakReference<ProgressBar> progressBar;
    List<RadioItem> fireBaseStreams = new ArrayList<>();


    public FirebaseItemsDataSource(RecyclerView rvRadioItems, ProgressBar progressBar) {
        this.rvRadioItems = new WeakReference<>(rvRadioItems);
        this.progressBar = new WeakReference<>(progressBar);
    }

    @Override
    protected Void doInBackground(Void... voids) {


        DatabaseReference streamsRef = FirebaseDatabase.getInstance().getReference().child("streams");

//        streams1.
        streamsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot radioSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post

                    RadioItem radioItem = radioSnapshot.getValue(RadioItem.class);
                    System.out.println("************************"+radioItem);
                    fireBaseStreams.add(radioItem);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.toString());
            }
        });
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        System.out.println("***** THIS IS THE LIST<<<<>>>> --" + fireBaseStreams);
        RecyclerView recycler = this.rvRadioItems.get(); //check if what rvSongs is pointing on , is not null
        ProgressBar pb = this.progressBar.get();


        if (pb == null) return;
        pb.setVisibility(View.GONE);

        if (recycler == null) return;

        Context context = recycler.getContext();
        RadioItemsAdapter adapter = new RadioItemsAdapter(fireBaseStreams,recycler , pb, context);
        recycler.setAdapter(adapter);

        recycler.setLayoutManager(new LinearLayoutManager(context));//may change to a different manager (grid, staggered..)
        System.out.println("Refresh");
    }
}
