package com.example.yanqijs.cameraresult.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.yanqijs.cameraresult.R;
import com.example.yanqijs.cameraresult.activity.CameraActivity;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements SurfaceHolder.Callback {

    private RelativeLayout mGallery;
    private CameraClick cameraClick;
    private CameraImp mCameraImp;
    private Button cameraButton;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private CameraActivity mCameraActivity;


    public FirstFragment(CameraImp cameraImp, CameraActivity cameraActivity) {
        // Required empty public constructor
        if (cameraClick == null) {
            cameraClick = new CameraClick();
        }
        this.mCameraImp = cameraImp;
        mCameraActivity = cameraActivity;
    }

    public void setmCameraImp(CameraImp cameraImp) {
        mCameraImp = cameraImp;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photoshop, container, false);
        mGallery = (RelativeLayout) view.findViewById(R.id.btn_gallery);
        cameraButton = (Button) view.findViewById(R.id.btn_take_picture);
        mSurfaceView = (SurfaceView) view.findViewById(R.id.surface);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
        try {
            mCameraActivity.mCamera.setPreviewDisplay(mSurfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCameraActivity.mCamera.setDisplayOrientation(90);//设置相机是否翻转
        cameraButton.setOnClickListener(cameraClick);
        mGallery.setOnClickListener(cameraClick);
        return view;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mCameraActivity.startCamera();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mCameraActivity.mCamera.stopPreview();
        mCameraActivity.startCamera();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCameraActivity.releaseCamera();
    }

    public class CameraClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_take_picture:
                    mCameraImp.CameraPicture();
                    break;
                case R.id.btn_gallery:
                    mCameraImp.OpenGalery();
                    break;
                default:
                    break;
            }
        }
    }

    public interface CameraImp {
        void CameraPicture();

        void OpenGalery();
    }

}
