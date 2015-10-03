package com.example.michal.mpcriminalintent;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;

/**
 * Created by Michal on 10/3/2015.
 */
public class CrimeListFragment extends ListFragment
{
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // getActivity() is a convenience methods that returns the hosting activity and allows a
        // fragment to handle more of the activity's affairs. setTitle(int) is called to change
        // what is displayed on the action bar.
        getActivity().setTitle(R.string.crimes_title);
        // Gets the CrimeLab singleton and then gets the list of crimes.
        mCrimes = CrimeLab.get(getActivity()).getCrimes();
    }
} // End class CrimeListFragment
