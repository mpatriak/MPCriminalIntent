package com.example.michal.mpcriminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Michal on 10/3/2015.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity
{
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Sets the view to be inflated from activity_fragment.xml.
        setContentView(R.layout.activity_fragment);
        // Gets the fragment manager.
        FragmentManager fm = getSupportFragmentManager();
        // Looks for the fragment in the FragmentManager.
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        // If the fragment does not exist, it is created and added.
        if (fragment == null)
        {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
} // End class SingleFragmentActivity
