package com.example.michal.mpcriminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Michal on 11/11/2015.
 */
public class CrimeCameraActivity extends SingleFragmentActivity
{
    // Hides the status bar and action bar.
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        // Hide the window title.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Hide the status bar and other OS-level chrome.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment()
    {
        return new CrimeCameraFragment();
    }
} // End class CrimeCameraActivity.
