package com.example.michal.mpcriminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Michal on 10/3/2015.
 */
public class CrimeLab
{
    private ArrayList<Crime> mCrimes;

    private static CrimeLab sCrimeLab;
    private Context mAppContext;

    // Private constructor to implement CrimeLab as a singleton. The Context parameter allows the
    // singleton to start activities, access project resources, find application's private
    // storage...
    private CrimeLab(Context appContext)
    {
        mAppContext = appContext;
        mCrimes = new ArrayList<Crime>();

        // Populates the array list with 100 Crime objects.
        for (int i = 0; i < 100; i++)
        {
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            c.setSolved(i % 2 == 0); // Sets every other crime to solved.
            mCrimes.add(c);
        }
    }

    // Get method to retrieve a new instance of the CrimeLab singleton.
    public static CrimeLab get(Context c)
    {
        if (sCrimeLab == null)
        {
            // Calls getApplicationContext() to trade the passed-in Context for the application
            // context.
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    // Returns the array list.
    public ArrayList<Crime> getCrimes()
    {
        return mCrimes;
    }

    // Returns the Crime with the given ID.
    public Crime getCrime(UUID id)
    {
        for (Crime c : mCrimes)
        {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }
} // End class CrimeLab.
