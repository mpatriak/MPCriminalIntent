package com.example.michal.mpcriminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Michal on 10/3/2015.
 */
public class CrimeListActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        return new CrimeListFragment();
    }
} // End class CrimeListActivity
