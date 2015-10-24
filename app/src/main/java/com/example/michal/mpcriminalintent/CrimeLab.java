package com.example.michal.mpcriminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Michal on 10/5/2015.
 */

// This class is created as a singleton. To create singleton: create class with a private
// constructor and a get method that returns the instance. If the instance already exists, return
// the instance. If it does not exist, get() will call constructor to create it.
public class CrimeLab
{
    private ArrayList<Crime> mCrimes;

    private static CrimeLab sCrimeLab;
    private Context mAppContext;

    // Private constructor. The Context parameter allows the singleton to start activities,
    // access project resources, find application's private storage, etc...
    private CrimeLab(Context appContext)
    {
        mAppContext = appContext;
        // Creates an empty ArrayList.
        mCrimes = new ArrayList<Crime>();
        // Populates the ArrayList.
        /*for (int i = 0; i < 100; i++)
        {
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            c.setSolved(i % 2 == 0); // Every other one.
            mCrimes.add(c);
        }*/
    }

    // Get method to call constructor to create the instance. getApplicationContext() is called
    // to ensure the singleton has a long-term Context to work with, by trading the passed in
    // Context for the application context.
    public static CrimeLab get(Context c)
    {
        if (sCrimeLab == null)
        {
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    // Adds a Crime to the ArrayList when the user clicks the new Crime button
    public void addCrime(Crime c)
    {
        mCrimes.add(c);
    }

    // Returns the ArrayList.
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
} // End class CrimeLab
