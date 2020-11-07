package com.example.tbnaaproject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList = new ArrayList<>();
    private int numOfTabs;

    public ViewPagerAdapter(FragmentManager manager, int numOfTabs) {
        super(manager);
        this.numOfTabs = numOfTabs;
        addFragments();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    public void addFragments(){
        Fragment adoption = new AdoptionRequests();
        Fragment adding = new AddingRequests();
        Fragment profile = new UserProfileFields();

        mFragmentList.add(adding);
        mFragmentList.add(adoption);
        mFragmentList.add(profile);
    }

}