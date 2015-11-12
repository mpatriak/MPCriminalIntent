package com.example.michal.mpcriminalintent;

import android.annotation.TargetApi;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;
import java.util.List;

/**
 * Created by Michal on 11/8/2015.
 */
public class CrimeCameraFragment extends Fragment
{
    private static final String TAG = "CrimeCameraFragment";

    private Camera mCamera;
    private SurfaceView mSurfaceView;

    // A simple algorithm to get the largest size available.
    private Camera.Size getBestSupportedSize(List<Camera.Size> sizes, int width, int height)
    {
        Camera.Size bestSize = sizes.get(0);
        int largestArea = bestSize.width * bestSize.height;
        for (Camera.Size s : sizes)
        {
            int area = s.width * s.height;
            if (area > largestArea)
            {
                bestSize = s;
                largestArea = area;
            }
        }
        return bestSize;
    }

    @Override
    @SuppressWarnings("deprecation")
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_crime_camera, parent, false);

        Button takePictureButton = (Button)v.findViewById(R.id.crime_camera_takePictureButton);
        takePictureButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                getActivity().finish();
            }
        });
        mSurfaceView = (SurfaceView)v.findViewById(R.id.crime_camera_surfaceView);
        // Gets the SurfaceView's SurfaceHolder.
        SurfaceHolder holder = mSurfaceView.getHolder();
        // setType() and SURFACE_TYPE_PUSH_BUFFERS are both deprecated, but are required for
        // Camera preview to work on pre-3.0 devices.
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        holder.addCallback(new SurfaceHolder.Callback()
        {
            public void surfaceCreated(SurfaceHolder holder)
            {
                // Tell the Camera to use this surface as its preview area.
                try
                {
                    if (mCamera != null)
                    {
                        mCamera.setPreviewDisplay(holder);
                    }
                } catch (IOException exception)
                {
                    Log.e(TAG, "Error setting up preview display", exception);
                }
            }

            public void surfaceDestroyed(SurfaceHolder holder)
            {
                // We can no longer display on this surface, so stop the preview.
                if (mCamera != null)
                {
                    mCamera.stopPreview();
                }
            }

            public void surfaceChanged(SurfaceHolder holder, int format, int w, int h)
            {
                if (mCamera == null) return;

                // The surface has changed size; update the camera preview size.
                Camera.Parameters parameters = mCamera.getParameters();
                Camera.Size s = getBestSupportedSize(parameters.getSupportedPreviewSizes(), w, h);
                parameters.setPreviewSize(s.width, s.height);
                mCamera.setParameters(parameters);
                try
                {
                    mCamera.startPreview();
                } catch (Exception e)
                {
                    // Release the Camera if it cannot be started.
                    Log.e(TAG, "Could not start preview", e);
                    mCamera.release();
                    mCamera = null;
                }
            }
        });

        return v;
    }

    // Initializes the Camera using a static method.
    @TargetApi(9)
    @Override
    public void onResume()
    {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD)
        {
            mCamera = Camera.open(0);
        } else
        {
            mCamera = Camera.open();
        }
    }

    // Releases the Camera resource.
    @Override
    public void onPause()
    {
        super.onPause();

        if (mCamera != null)
        {
            mCamera.release();
            mCamera = null;
        }
    }
}

