package com.example.yanqijs.cameraresult.adapter;

import android.content.Context;
import android.view.View;

import com.example.yanqijs.cameraresult.R;
import com.example.yanqijs.cameraresult.model.PhotoShopResultModel;

import java.util.List;


/**
 * Created by yanqijs on 2016/3/7.
 */
public class RecyclerAdapter extends BaseRecyclerAdapter<PhotoShopResultModel> {
    private View.OnClickListener onClickListener;


    public RecyclerAdapter(List<PhotoShopResultModel> mDatas, Context context, RecyclerImp onRecyclerImp) {
        super(mDatas, context, onRecyclerImp);
    }

    @Override
    public void init(BaseRecyclerHelper helper, int position, PhotoShopResultModel item) {
        helper.setText(R.id.name_text, item.getName());
        helper.setImage(R.id.image_good, R.mipmap.danpin);
        helper.setText(R.id.text_price, item.getPrice() + "");
        helper.setVisible(R.id.ziying_image, item.isZiying());

    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.item_graph_result;
    }

    @Override
    protected void setOnRecyclerImp(RecyclerImp onRecyclerImp) {
        this.onRecyclerImp = onRecyclerImp;
    }


}
