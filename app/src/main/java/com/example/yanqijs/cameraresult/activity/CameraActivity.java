package com.example.yanqijs.cameraresult.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.camera2.CameraCaptureSession;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.yanqijs.cameraresult.MyView;
import com.example.yanqijs.cameraresult.R;
import com.example.yanqijs.cameraresult.adapter.DividerGridItemDecoration;
import com.example.yanqijs.cameraresult.adapter.MyViewPagerAdapter;
import com.example.yanqijs.cameraresult.adapter.RecyclerAdapter;
import com.example.yanqijs.cameraresult.adapter.RecyclerImp;
import com.example.yanqijs.cameraresult.adapter.SwitchAdapter;
import com.example.yanqijs.cameraresult.fragment.FirstFragment;
import com.example.yanqijs.cameraresult.fragment.SecondFragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanqijs on 2016/4/14.
 */
public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback, Camera.PictureCallback, FirstFragment.CameraImp {

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera;
    private RecyclerView mRecyclerView;
    private FrameLayout mFrameLayout;
    private Boolean isResult = false;
    private ImageView imageView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mSurfaceView = (SurfaceView) findViewById(R.id.surface);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
//        mFrameLayout = (FrameLayout) findViewById(R.id.framelayout);
        imageView = (ImageView) findViewById(R.id.image);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        initViewPager();
        initCamera();
//        initFragment();
    }

    private void initViewPager() {
        Fragment firstFragment = new FirstFragment();
        Fragment secFragment = new SecondFragment();
        List<Fragment> datas = new ArrayList<>();
        datas.add(firstFragment);
        datas.add(secFragment);
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), datas);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.setCurrentItem(0);
    }

    private void initFragment() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        FirstFragment firstFragment = new FirstFragment();
//        firstFragment.setmCameraImp(this);
//        transaction.add(R.id.framelayout, firstFragment, "first");
//        transaction.commit();

    }


    private void initCamera() {

        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mCamera = Camera.open();//开启相机
        Camera.Parameters parameters = mCamera.getParameters();//获得默认参数
        parameters.setPictureFormat(PixelFormat.JPEG);// 设置照片的格式
        parameters.setJpegQuality(100);// 设置照片的质量
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//设置自动对焦
//        parameters.setFocusMode("auto");//设置自动对焦
        mCamera.setDisplayOrientation(90);//设置相机是否翻转
        mCamera.setParameters(parameters);
        try {
            mCamera.setPreviewDisplay(mSurfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCamera.startPreview();
        mCamera.takePicture(null, null, null, this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCamera.stopPreview();
        mCamera.release();
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Log.i("camera", "takepicture");
        if (isResult) {
            Bitmap resource = Bytes2Bimap(data);
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            resource.compress(Bitmap.CompressFormat.JPEG,99,stream);
            Matrix matrix = new Matrix();
            matrix.setRotate(90);
            resource = Bitmap.createBitmap(resource, 0, 0, resource.getWidth(), resource.getHeight(), matrix, true);
            Log.i("size", "MyView.width:" + MyView.width + "MyView.height" + MyView.height
                    + "MyView.PICTURE_WIDTH" + MyView.PICTURE_WIDTH + "MyView.PICTURE_HEIGHT:" + MyView.PICTURE_HEIGHT
                    + "bitmapwidth" + resource.getWidth() + "bitmapHeight:" + resource.getHeight());
            int tempWidth = (resource.getWidth() - MyView.PICTURE_WIDTH) / 2;
            int tempHeight = (resource.getHeight() - 280 - MyView.PICTURE_HEIGHT) / 2;

            Bitmap bitmap = Bitmap.createBitmap(resource, tempWidth, tempHeight,
                    MyView.PICTURE_WIDTH, MyView.PICTURE_HEIGHT);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(bitmap);
        }
        mCamera.startPreview();
    }

    @Override
    public void CameraPicture() {
        isResult = true;
        mCamera.takePicture(null, null, null, this);
    }

    @Override
    public void OpenGalery() {

    }

    public Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }
}
