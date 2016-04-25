package com.example.yanqijs.cameraresult;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by yanqijs on 2016/4/14.
 */
public class MyView extends View {

    private Paint mPaint;
    private Rect mBounds;
    private Context mContext;
    public static float mScale;
    public static int width;
    public static int height;
    public static int PICTURE_WIDTH;
    public static int PICTURE_HEIGHT;

    public MyView(Context context) {
        super(context);
        this.mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mScale = mContext.getResources().getDisplayMetrics().density;

        //// TODO: 2016/4/14  针对不同的分辨率设置不同的宽高！！
        PICTURE_WIDTH = dip2px(255);
        width = (getWidth() - PICTURE_WIDTH) / 2;
        PICTURE_HEIGHT = dip2px(340);
        height = (getHeight() - PICTURE_HEIGHT) / 2;
        float roundSize = 6;
        float roundLong = 30;
        Log.i("view", getWidth() + "!!!!" + getHeight() + "");
        //绘制四周边框
        mPaint.setAlpha(102);
        mPaint.setColor(getResources().getColor(R.color.colorhide));
        canvas.drawRect(0, 0, width, getHeight(), mPaint);
        canvas.drawRect(getWidth() - width, 0, getWidth(), getHeight(), mPaint);
        canvas.drawRect(width, 0, getWidth() - width, height, mPaint);
        canvas.drawRect(width, getHeight() - height, getWidth() - width, getHeight(), mPaint);
        //绘制边框
        mPaint.setColor(getResources().getColor(R.color.colorBorder));
        canvas.drawRect(width + 1, height + 1, width + 2, getHeight() - height - 1, mPaint);//left
        canvas.drawRect(width + 1, height + 1, getWidth() - width - 1, height + 2, mPaint);//top
        canvas.drawRect(getWidth() - width - 2, height + 1, getWidth() - width - 1, getHeight() - height - 1, mPaint);//right
        canvas.drawRect(width + 1, getHeight() - height - 2, getWidth() - width - 1, getHeight() - height - 1, mPaint);//bottom
        //绘制边角
        mPaint.setColor(getResources().getColor(R.color.colorRoundRed));
        canvas.drawRect(width + 1, height + 1, width + 1 + 6, height + 1 + 30, mPaint);//left_top
        canvas.drawRect(width + 1, getHeight() - height - 1 - 30, width + 1 + 6, getHeight() - height - 1, mPaint);//left_bottom
        canvas.drawRect(width + 1, height + 1, width + 1 + 30, height + +1 + 6, mPaint);//top_left
        canvas.drawRect(getWidth() - width - 1 - 30, height + 1, getWidth() - width - 1, height + +1 + 6, mPaint);//top_right
        canvas.drawRect(getWidth() - width - 1 - 6, height + 1, getWidth() - width - 1, height + 1 + 30, mPaint);//right_top
        canvas.drawRect(getWidth() - width - 1 - 6, getHeight() - height - 30 - 1, getWidth() - width - 1, getHeight() - height - 1, mPaint);//right_bottom
        canvas.drawRect(width + 1, getHeight() - height - 1 - 6, width + 1 + 30, getHeight() - height - 1, mPaint);//bottom_left
        canvas.drawRect(getWidth() - width - 1 - 30, getHeight() - height - 1 - 6, getWidth() - width - 1, getHeight() - height - 1, mPaint);//bottom_right
    }

    public int px2dip(float pxValue) {
        return (int) (pxValue / mScale + 0.5f);
    }

    public static int dip2px(float dpValue) {
        return (int) (dpValue * mScale + 0.5f);
    }
}
