package com.example.radiobe.mainScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.radiobe.radio.MyItemsRepository;
import com.example.radiobe.R;
import com.example.radiobe.radio.RadioItem;
import com.example.radiobe.radio.RadioItemsAdapter;
import com.example.radiobe.radio.RadioItemsDataSource;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.radiobe.radio.MyItemsRepository.radioItems;


public class AllPrograms extends Fragment {
    private RecyclerView recyclerView;
    private RadioItemsAdapter adapter;
    private SearchView sv;
    public static TabLayout tabs;
    private List<RadioItem> filteredByYarden; // need to check that


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
        adapter = new RadioItemsAdapter(RadioItemsDataSource.getItems(),getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        tabs = view.findViewById(R.id.tabLayout);
        //sv = view.findViewById(R.id.searchView);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()){
                    case 0:
                        break;
                    case 1:
                         filteredByYarden = MyItemsRepository.filterByYarden(radioItems);

//                        recyclerView.setAdapter(new RadioItemsAdapter(new MyItemsRepository().filterByName(RadioItemsDataSource.getItems()), getContext()));
                        break;
                    case 2:
                        filteredByYarden = MyItemsRepository.filterByYarden(radioItems);
                        break;
                    case 3:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
}
