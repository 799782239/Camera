package com.example.yanqijs.cameraresult.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yanqijs.cameraresult.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    private RelativeLayout mCamera;
    private RelativeLayout mGallery;
    private CameraClick cameraClick;
    private CameraImp mCameraImp;
    private Button cameraButton;


    public FirstFragment() {
        // Required empty public constructor
        if (cameraClick == null) {
            cameraClick = new CameraClick();
        }

    }

    public void setmCameraImp(CameraImp cameraImp) {
        this.mCameraImp = cameraImp;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, null);
        mCamera = (RelativeLayout) view.findViewById(R.id.btn_camera);
        mGallery = (RelativeLayout) view.findViewById(R.id.btn_gallery);
        cameraButton = (Button) view.findViewById(R.id.btn_take_picture);
        cameraButton.setOnClickListener(cameraClick);
        mCamera.setOnClickListener(cameraClick);
        mGallery.setOnClickListener(cameraClick);
        return view;
    }

    public class CameraClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_camera:
                    mCameraImp.CameraPicture();
                    break;
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
