package com.example.radiobe.fragments;

import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.radiobe.R;
import com.example.radiobe.database.FirebaseItemsDataSource;
import com.example.radiobe.database.RadioItemsDataSource;
import com.facebook.FacebookActivity;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;



public class AllPrograms extends Fragment {
    private RecyclerView recyclerView;
//    private RadioItemsAdapter adapter;

    RadioItemsDataSource source;
    private SearchView searchView;

    private TabLayout tabs;
    private ProgressBar progressBar;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String filePath = intent.getStringExtra("stream_url");
            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(filePath))
                    .build();

            ShareDialog.show(AllPrograms.this, content);

        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_all_programs, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler);
        progressBar = view.findViewById(R.id.progressBar);

        searchView = view.findViewById(R.id.searchView);
        if (getActivity() != null) {

            System.out.println("SearchManager Should Work");
            SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getActivity().getComponentName()));
        }


//        new RadioItemsDataSource(recyclerView, progressBar).execute();
        source = new RadioItemsDataSource(recyclerView, progressBar);
        source.execute();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (source.adapter != null) {
                    System.out.println("Not Null");
                    source.adapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (source.adapter != null) {
                    System.out.println("Not Null");
                    source.adapter.getFilter().filter(newText);
                }
                return false;
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver, new IntentFilter("share_facebook"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
    }


}

//        new RadioItemsDataSource(recyclerView, progressBar).execute();
