package com.example.radiobe.mainScreen;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class MainScreenAdapter extends FragmentPagerAdapter {

        public MainScreenAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {

            switch(position) {
                case 0:
                    return new RadioLive();
//                    return new AllPrograms();
                case 1:
                    return new Notifications();
                case 2:
                    return new Favorites();
                case 3:
                    return new AllPrograms();
            }
            throw new IllegalArgumentException("No");
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
    }