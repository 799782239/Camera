package com.example.yanqijs.cameraresult.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanqijs on 2016/4/15.
 */
public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    private String[] title = {"拍封aaa", "扫码条识书"};
    private List<Fragment> fragment=new ArrayList<>();

    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragment = fragments;
    }

    @Override
    public int getCount() {
        return fragment.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragment.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
