package com.example.radiobe.radio;

import java.util.ArrayList;
import java.util.List;

public class MyItemsRepository {
    public static List<RadioItem> radioItems = RadioItemsDataSource.getItems();

    public MyItemsRepository(){}

    public static List<RadioItem> filterByName(){

        List<RadioItem> afterFilter = new ArrayList<>();
        for (int i = 0; i < radioItems.size(); i++) {
            if(radioItems.get(i).getShadran().equals("Shahaf Tepler"))
                afterFilter.add(radioItems.get(i));
        }

        return afterFilter;
    }

    public static List<RadioItem> filterByYarden(List<RadioItem> items){

        List<RadioItem> afterYardenFilter = new ArrayList<>();
        for (int i = 0; i < radioItems.size(); i++) {
            if(radioItems.get(i).getShadran().equals("Yarden Swisa"))
                afterYardenFilter.add(radioItems.get(i));
        }

        return afterYardenFilter;
    }

    public static List<RadioItem> filterByDaniel(List<RadioItem> items){

        List<RadioItem> afterDanielFilter = new ArrayList<>();
        for (int i = 0; i < radioItems.size(); i++) {
            if(radioItems.get(i).getShadran().equals("Daniel Mizrahi"))
                afterDanielFilter.add(items.get(i));
        }

        return afterDanielFilter;
    }

}
