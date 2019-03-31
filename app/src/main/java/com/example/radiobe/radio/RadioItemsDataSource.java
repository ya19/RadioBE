package com.example.radiobe.radio;

import com.example.radiobe.R;

import java.util.ArrayList;
import java.util.List;

public class RadioItemsDataSource {

    // here we will use the API
    public static List<RadioItem> getItems(){
        List<RadioItem> items = new ArrayList<>();

                                        //TODO solve the local date issue.
        for (int i = 0; i < 50; i++) {

        items.add(new RadioItem("Shahaf Tepler","23:20", null ,0,0,0, R.id.tbPlayStop));
        items.add(new RadioItem("Yarden Swisa","1:30:20", null ,0,0,0,R.id.tbPlayStop));
        items.add(new RadioItem("Daniel Mizrahi","40:21",null,0,0,0,R.id.tbPlayStop));
        items.add(new RadioItem("Amitay a gever","30:21",null ,0,0,0,R.id.tbPlayStop));
        }

        return items;
    }
}
