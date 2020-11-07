package com.example.tbnaaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class UserProfile2 extends AppCompatActivity {
    ViewPager mViewPager;
    TabLayout mTabLayout;
    ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_2);

        mTabLayout = (TabLayout) findViewById(R.id.userProfileTabs);
        mViewPager = (ViewPager) findViewById(R.id.userProfilePager);

        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager mViewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragments();
        mViewPager.setAdapter(adapter);
    }
}