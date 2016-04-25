package com.example.yanqijs.cameraresult.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanqijs on 2016/4/18.
 */
public class Viewpager extends PagerAdapter {
    private List<View> mListViews;
    private List<String> mData=new ArrayList<>();

    public Viewpager(List<View> mListViews) {
        this.mListViews = mListViews;
        mData.add("拍封面识书");
        mData.add("扫条码识书");
    }

    @Override
    public int getCount() {
        return mListViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mListViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mListViews.get(position), 0);
        return mListViews.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mData.get(position);
    }
}
