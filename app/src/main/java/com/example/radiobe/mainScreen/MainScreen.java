package com.example.radiobe.mainScreen;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.radiobe.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

public class MainScreen extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    FragmentManager fm;
    ViewPager viewPager;
    MainScreenAdapter mMainScreenAdapter;
    BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container, new AllPrograms()).commit();

//        navigation.setSelectedItemId(R.id.navigation_home);

        viewPager = findViewById(R.id.container);

        mMainScreenAdapter = new MainScreenAdapter(fm);
        viewPager.setAdapter(mMainScreenAdapter);
        viewPager.setCurrentItem(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 3:
                        navigation.setSelectedItemId(R.id.navigation_home);
                        break;

                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_favorites);
                        break;

                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_notifications);
                        break;

                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_radio);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch(menuItem.getItemId()){
            case R.id.navigation_home:
//                fm.beginTransaction().replace(R.id.container, new AllPrograms()).commit();
                viewPager.setCurrentItem(3);
                return true;

            case R.id.navigation_favorites:
//                fm.beginTransaction().replace(R.id.container, new Favorites()).commit();
                viewPager.setCurrentItem(2);

                return true;

            case R.id.navigation_notifications:
//                fm.beginTransaction().replace(R.id.container, new Notifications()).commit();
                viewPager.setCurrentItem(1);
                return true;

            case R.id.navigation_radio:
//                fm.beginTransaction().replace(R.id.container, new RadioLive()).commit();
                viewPager.setCurrentItem(0);
                return true;
        }

//        throw new IllegalArgumentException("No such Button!");
        return false;
    }
}
