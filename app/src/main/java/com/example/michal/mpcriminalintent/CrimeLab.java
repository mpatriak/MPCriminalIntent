package com.example.michal.mpcriminalintent;

import android.content.Context;
import android.util.Log;

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
    private static final String TAG = "CrimeLab";
    private static final String FILENAME = "crimes.json";

    private ArrayList<Crime> mCrimes;
    private CriminalIntentJSONSerializer mSerializer;

    private static CrimeLab sCrimeLab;
    private Context mAppContext;

    // Private constructor. The Context parameter allows the singleton to start activities,
    // access project resources, find application's private storage, etc...
    private CrimeLab(Context appContext)
    {
        mAppContext = appContext;

        mSerializer = new CriminalIntentJSONSerializer(mAppContext, FILENAME);

        try
        {
            mCrimes = mSerializer.loadCrimes();
        } catch (Exception e)
        {
            mCrimes = new ArrayList<Crime>();
            Log.e(TAG, "Error loading crimes: ", e);
        }

        // Creates an empty ArrayList.
        //mCrimes = new ArrayList<Crime>();
        // Creates an instance of the JSONSerializer.
        mSerializer = new CriminalIntentJSONSerializer(mAppContext, FILENAME);

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

    // Tries to serialize the crimes and returns a Boolean indicating success.
    public boolean saveCrimes()
    {
        try
        {
            mSerializer.saveCrimes(mCrimes);
            Log.d(TAG, "crimes saved to file");
            return true;
        } catch (Exception e)
        {
            Log.e(TAG, "Error saving crimes: ", e);
            return false;
        }
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
