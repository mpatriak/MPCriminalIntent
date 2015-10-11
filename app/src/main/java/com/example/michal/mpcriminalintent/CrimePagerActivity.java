package com.example.michal.mpcriminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Michal on 10/7/2015.
 */
public class CrimePagerActivity extends AppCompatActivity
{
    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Instantiates the ViewPager.
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        // Sets the ViewPager as the content view.
        setContentView(mViewPager);

        // Gets the data set, the ArrayList of crimes, from CrimeLab.
        mCrimes = CrimeLab.get(this).getCrimes();

        // Gets the activity's instance of Fragment Manager.
        FragmentManager fm = getSupportFragmentManager();
        // Sets the adapter to be an unnamed instance of FragmentStatePagerAdapter. The
        // FragmentStatePagerAdapter adds the fragments returned to the activity and helps
        // ViewPager identify the fragments' views so that they can be placed correctly.
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm)
        {
           @Override
           // Returns how many items there are in the ArrayList.
            public int getCount()
           {
               return mCrimes.size();
           }

            @Override
            public Fragment getItem(int pos)
            {
                // Fetches the Crime instance for the given position in the dataset.
                Crime crime = mCrimes.get(pos);
                // The Crime's ID is used to create and return a properly configured CrimeFragment.
                return CrimeFragment.newInstance(crime.getId());
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            public void onPageScrollStateChanged(int state) {}

            public void onPageScrolled(int pos, float posOffset, int posOffsetPixels) {}

            public void onPageSelected(int pos)
            {
                Crime crime = mCrimes.get(pos);
                if (crime.getTitle() != null)
                {
                    setTitle(crime.getTitle());
                }
            }
        });

        UUID crimeId = (UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for (int i = 0; i < mCrimes.size(); i++)
        {
            if (mCrimes.get(i).getId().equals(crimeId))
            {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
} // End class CrimePagerActivity
