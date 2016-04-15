package com.example.yanqijs.cameraresult.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.example.yanqijs.cameraresult.R;

import java.util.List;

/**
 * Created by yanqijs on 2016/4/14.
 */
public class SwitchAdapter extends BaseRecyclerAdapter<String> {
    public SwitchAdapter(List<String> mDatas, Context context, RecyclerImp onRecyclerImp) {
        super(mDatas, context, onRecyclerImp);
    }

    @Override
    public void init(BaseRecyclerHelper helper, int position, String item) {
        helper.setText(R.id.text_switch, item);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.item_switch;
    }

    @Override
    protected void setOnRecyclerImp(RecyclerImp onRecyclerImp) {

    }
}
