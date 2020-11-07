package com.example.tbnaaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class UserProfile2 extends AppCompatActivity {
    ViewPager mViewPager;
    TabLayout mTabLayout;
    TabItem profileTab, adoptionTab, addingTab;
    ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_2);

        mTabLayout = (TabLayout) findViewById(R.id.userProfileTabs);
        mViewPager = (ViewPager) findViewById(R.id.userProfilePager);
        profileTab = (TabItem) findViewById(R.id.profileInfoTab);
        adoptionTab = (TabItem) findViewById(R.id.adoptionRequestTab);
        addingTab = (TabItem) findViewById(R.id.addingRequestTab);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

}