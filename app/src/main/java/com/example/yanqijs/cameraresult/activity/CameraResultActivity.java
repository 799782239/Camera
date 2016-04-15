package com.example.yanqijs.cameraresult.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.yanqijs.cameraresult.R;
import com.example.yanqijs.cameraresult.adapter.DividerGridItemDecoration;
import com.example.yanqijs.cameraresult.adapter.RecyclerAdapter;
import com.example.yanqijs.cameraresult.adapter.RecyclerImp;
import com.example.yanqijs.cameraresult.model.PhotoShopResultModel;

import java.util.ArrayList;
import java.util.List;

public class CameraResultActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private RecyclerAdapter mRecyclerAdapter;
    private List<PhotoShopResultModel> mDatas = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_result);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        initData();
        init();

    }

    private void initData() {
        PhotoShopResultModel data = new PhotoShopResultModel();
        data.setImageUrl("a");
        data.setName("书名");
        data.setPrice((float) 12.98);
        data.setZiying(true);
        mDatas.add(data);
        data = new PhotoShopResultModel();
        data.setImageUrl("a");
        data.setName("书名");
        data.setPrice((float) 12.98);
        data.setZiying(true);
        mDatas.add(data);
        data = new PhotoShopResultModel();
        data.setImageUrl("a");
        data.setName("书名");
        data.setPrice((float) 12.98);
        data.setZiying(true);
        mDatas.add(data);
        data = new PhotoShopResultModel();
        data.setImageUrl("a");
        data.setName("书名");
        data.setPrice((float) 12.98);
        data.setZiying(true);
        mDatas.add(data);
        data = new PhotoShopResultModel();
        data.setImageUrl("a");
        data.setName("书名");
        data.setPrice((float) 12.98);
        data.setZiying(true);
        mDatas.add(data);
        data = new PhotoShopResultModel();
        data.setImageUrl("a");
        data.setName("书名");
        data.setPrice((float) 12.98);
        data.setZiying(true);
        mDatas.add(data);
        data = new PhotoShopResultModel();
        data.setImageUrl("a");
        data.setName("书名");
        data.setPrice((float) 12.98);
        data.setZiying(true);
        mDatas.add(data);
    }

    private void init() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerAdapter = new RecyclerAdapter(mDatas, this, new RecyclerImp() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(getApplication(), position + "", Toast.LENGTH_SHORT).show();
            }
        });
        recycler.setAdapter(mRecyclerAdapter);
        recycler.setLayoutManager(gridLayoutManager);
        DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(this);
        recycler.addItemDecoration(dividerGridItemDecoration);
    }
}
