package com.example.yanqijs.cameraresult.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.yanqijs.cameraresult.R;
import com.example.yanqijs.cameraresult.adapter.MyViewPagerAdapter;
import com.example.yanqijs.cameraresult.adapter.Viewpager;
import com.example.yanqijs.cameraresult.fragment.FirstFragment;
import com.example.yanqijs.cameraresult.fragment.SecondFragment;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        List<View> a = new ArrayList<>();
        LayoutInflater lf = getLayoutInflater().from(this);
        View view = lf.inflate(R.layout.fragment_first, null);
        View view1 = lf.inflate(R.layout.fragment_second, null);
        a.add(view);
        a.add(view1);
//        List<Fragment> fragement = new ArrayList<>();
//        fragement.add(new FirstFragment());
//        fragement.add(new SecondFragment());
        mViewpager.setAdapter(new Viewpager(a));
    }
}
