package com.ulianasisoeva.angryalarm.screen;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ulianasisoeva.angryalarm.R;


public class TabLayoutActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    TabItem tabAlarm;
    TabItem tabDemo;
    ViewPager viewPager;
    PageAdapter pageAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitle(getResources().getString(R.string.app_name));
        tabLayout = findViewById(R.id.tablayout);


        tabAlarm = findViewById(R.id.alarm_tab);
        tabDemo = findViewById(R.id.demo_tab);
        viewPager = findViewById(R.id.viewPager);

        viewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}
