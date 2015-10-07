package com.example.michal.mpcriminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity
{

   @Override
    protected Fragment createFragment()
   {
       //return new CrimeFragment();

       // Retrieves the extra from CrimeActivity's intent.
       UUID crimeId = (UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);

       //  CrimeActivity's intent is passed into the new instance.
       return CrimeFragment.newInstance(crimeId);
   }

} // End class CrimeActivity.
