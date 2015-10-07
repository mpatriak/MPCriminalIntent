package com.example.michal.mpcriminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Michal on 10/6/2015.
 */

// The default implementation of ListFragment inflates a layout that defines a full-screen
// ListView. This is the layout that will be used for now.
public class CrimeListFragment extends ListFragment
{
    private static final String TAG = "CrimeListFragment";

    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Sets the title of the activity that will host the fragment. getActivity() is a
        // fragment convenience method that returns the hosting activity and allows a fragment to
        // handle more of the activities affairs.
        getActivity().setTitle(R.string.crimes_title);
        // Gets the CrimeLab singleton and then gets the list of crimes.
        mCrimes = CrimeLab.get(getActivity()).getCrimes();

       CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        // This is a ListFragment convenience method that can be used to set the adapter of the
        // implicit ListView managed by CrimeListFragment
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);

        // Start CriminalActivity
        // CrimeListFragment creates an explicit intent that names the CrimeActivity class.
        // getActivity() is used to pass its hosting activity as the Context object that the
        // Intent constructor requires.
        Intent i = new Intent(getActivity(), CrimeActivity.class);
        // Making mCrimeId an Intent extra tells CrimeFragment which Crime to display. This
        // passes a string key and the value to pair it with.
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
        startActivity(i);
    }

    // Subclass of ArrayAdapter as an inner class of CrimeListFragment.
    private class CrimeAdapter extends ArrayAdapter<Crime>
    {
        public CrimeAdapter(ArrayList<Crime> crimes)
        {
            // This call to the superclass constructor is required to properly hook up the
            // dataset of Crimes. 0 can be passed for the layout ID because a pre-defined layout
            // is not being used.
            super(getActivity(), 0, crimes);
        }

        // Returns a view inflated from the custom layout and populated with the correct Crime data.
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // Determines whether a recycled view has been passed in. If not, a layout is
            // inflated from the custom layout.
            if (convertView == null)
            {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime,
                        null);
            }

            // Configure the view for this crime. Gets the Crime for the current position in the
            // list.
            Crime c = getItem(position);

            // Gets a reference to each widget in the view object and configures it with the
            // Crime's data.
            TextView titleTextView = (TextView)convertView.findViewById(R.id
                    .crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());
            TextView dateTextView = (TextView)convertView.findViewById(R.id
                    .crime_list_item_dateTextView);
            dateTextView.setText(c.getDate().toString());
            CheckBox solvedCheckBox = (CheckBox)convertView.findViewById(R.id
                    .crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());

            // Returns the view object to the ListView.
            return convertView;
        }
    }

    // Updates the fragment's view by reloading the list.
    @Override
    public void onResume()
    {
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }
} // End class CrimeListFragment
