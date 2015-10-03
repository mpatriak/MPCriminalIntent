package com.example.michal.mpcriminalintent;

import android.support.v4.app.Fragment;

public class CrimeActivity extends SingleFragmentActivity
{

    @Override
    protected Fragment createFragment()
    {
        return new CrimeFragment();
    }

} // End class CrimeActivity.
