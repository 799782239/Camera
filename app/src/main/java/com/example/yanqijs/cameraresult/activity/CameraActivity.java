package com.example.yanqijs.cameraresult.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.yanqijs.cameraresult.MyView;
import com.example.yanqijs.cameraresult.R;
import com.example.yanqijs.cameraresult.adapter.MyViewPagerAdapter;
import com.example.yanqijs.cameraresult.adapter.Viewpager;
import com.example.yanqijs.cameraresult.fragment.FirstFragment;
import com.example.yanqijs.cameraresult.fragment.SecondFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanqijs on 2016/4/14.
 */
public class CameraActivity extends AppCompatActivity implements Camera.PictureCallback, FirstFragment.CameraImp {

    //    private SurfaceView mSurfaceView;
//    private SurfaceHolder mSurfaceHolder;
    public Camera mCamera;
    //    private RecyclerView mRecyclerView;
    private FrameLayout mFrameLayout;
    private Boolean isResult = false;
    private ImageView imageView;
    private ViewPager viewPager;
    private PagerTitleStrip pagerTabStrip;//一个viewpager的指示器，效果就是一个横的粗的下划线

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

//        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
//        mFrameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        imageView = (ImageView) findViewById(R.id.image);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        initCamera();
        initViewPagerChange();
        initViewPager();
//        initFragment(R.layout.fragment_first);
    }

    private void initViewPagerChange() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
//                        initFragment(R.layout.fragment_first);
                        break;
                    case 1:
//                        initFragment(R.layout.fragment_second);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViewPager() {
        pagerTabStrip = (PagerTitleStrip) findViewById(R.id.pagertab);
        pagerTabStrip.setTextColor(getResources().getColor(R.color.colorRed));
        pagerTabStrip.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        pagerTabStrip.setTextSpacing(15);
//        List<View> a = new ArrayList<>();
//        LayoutInflater lf = getLayoutInflater().from(this);
//        View view = lf.inflate(R.layout.fragment_second, null);
//        View view1 = lf.inflate(R.layout.fragment_second, null);
//        a.add(view);
//        a.add(view1);
        List<Fragment> a = new ArrayList<>();
        Fragment fragment1 = new FirstFragment(this, this);
        Fragment fragment2 = new SecondFragment();
        a.add(fragment1);
        a.add(fragment2);
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), a));
    }

//    private void initFragment(int ViewId) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        switch (ViewId) {
//            case R.layout.fragment_first:
//
//                FirstFragment firstFragment = new FirstFragment();
//                firstFragment.setmCameraImp(this);
//                transaction.replace(R.id.frameLayout, firstFragment, "first");
//                break;
//            case R.layout.fragment_second:
//                SecondFragment second = new SecondFragment();
//                transaction.replace(R.id.frameLayout, second, "second");
//                break;
//        }
//        transaction.commit();
//
//    }


    private void initCamera() {

        if (mCamera == null) {

            mCamera = Camera.open();//开启相机
        }

//        mSurfaceView.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//                                                mCamera.autoFocus(null);
//                                            }
//                                        }
//        );
    }

    public void startCamera() {


        mCamera.startPreview();

        mCamera.autoFocus(null);
    }

//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        startCamera();
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//        mCamera.stopPreview();
//        startCamera();
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//        releaseCamera();
//    }

    public void releaseCamera() {
        if (mCamera != null) {

            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
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
            WindowManager windowManager = getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            int MatrixWidth = (MyView.PICTURE_WIDTH * resource.getWidth()) / display.getWidth();
            int MatrixHeight = (MyView.PICTURE_HEIGHT * resource.getHeight()) / (display.getHeight());
            int tempWidth = (resource.getWidth() - MatrixWidth) / 2;
            int tempHeight = (resource.getHeight() - MatrixHeight - MyView.dip2px(140)) / 2;

            Bitmap bitmap = Bitmap.createBitmap(resource, tempWidth, tempHeight,
                    MatrixWidth, (MyView.PICTURE_HEIGHT * MatrixWidth) / MyView.PICTURE_WIDTH);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(bitmap);
        }
        mCamera.startPreview();
    }

    @Override
    public void CameraPicture() {
        Log.i("camera", "jinlaile");
        isResult = true;
        if (mCamera == null) {
            Log.i("nulllll", "nullllllllll");
        }
        Camera.Parameters parameters = mCamera.getParameters();//获得默认参数
        parameters.setPictureFormat(PixelFormat.JPEG);// 设置照片的格式
        parameters.setJpegQuality(100);// 设置照片的质量
        for (int i = 0; i < parameters.getSupportedPictureSizes().size(); i++) {
            Log.i("sizeHei:", parameters.getSupportedPictureSizes().get(i).height + "");
            Log.i("sizewid:", parameters.getSupportedPictureSizes().get(i).width + "");
        }
//        Log.i("size",parameters.getSupportedPictureSizes().get(0).width+"");
        parameters.setPictureSize(1280, 960);
//        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//设置自动对焦
//        parameters.setFocusMode("auto");//设置自动对焦
        mCamera.setParameters(parameters);
        mCamera.takePicture(null, null, null, this);
    }

    @Override
    public void OpenGalery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 20);

    }

    public Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera == null) {

            initCamera();
            startCamera();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mCamera == null) {

            initCamera();
            startCamera();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20) {
            Bitmap bitmap = stringtoBitmap(searchUriFile(this, data));
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(bitmap);
        }
    }

    public static String searchUriFile(Context context, Intent data) {
        String localFile = null;
        if (null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor == null) {
                return null;
            }
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            localFile = cursor.getString(columnIndex);
            cursor.close();
        }
        return localFile;
    }

    public static Bitmap stringtoBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
