package com.example.michal.mpcriminalintent;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Michal on 10/1/2015.
 */
public class CrimeFragment extends Fragment
{
    // Key for extra.
    public static final String EXTRA_CRIME_ID = "com.example.michal.mpcriminalintent.crime_id";
    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_DATE = 0;

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private java.text.DateFormat dateFormat;

    // newInstance() method that accepts a UUID, creates an arguments bundle, creates a fragment
    // instance, and then attaches the arguments to the fragment.
    public static CrimeFragment newInstance(UUID crimeId)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    // Private method that encapsulates the code used to set the date button's text.
    private void updateDate()
    {
        //mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setText(dateFormat.format(mCrime.getDate()));
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // getIntent() returns the Intent that was used to start the activity
        // .getSerializableExtra(String) is called on the Intent to pull the UUID out into a
        // variable.

        setHasOptionsMenu(true);
        //UUID crimeId = (UUID)getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        //Retrieves the UUID from the fragment arguments.
        UUID crimeId = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);
        // Used to fetch the Crime from the CrimeLab once the ID is retrieved.
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
        dateFormat = android.text.format.DateFormat.getLongDateFormat(this.getActivity());
        getActivity().setTitle(R.string.crimes_title);


    }

    // Inflates fragment_crime.xml
    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        // Passes the layout resource ID
        // False parameter means fragment will be inflated in code.
        View v = inflater.inflate(R.layout.fragment_crime, parent, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            if (NavUtils.getParentActivityName(getActivity()) != null)
            {
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                activity.getSupportActionBar().setIcon(R.mipmap.ic_launcher);
            }
        }

        // Gets a reference to the EditText and adds a listener.
        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        // Sets the title for the Crime view.
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            // This method returns a string, which will be used to set the Crime's title.
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                mCrime.setTitle(c.toString());
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // This space intentionally left blank.
            }

            public void afterTextChanged(Editable c) {
                // This space intentionally left blank.
            }
        });

        // Gets a reference to the new button, sets its text as the date of the crime, and disabled
        // it for now.
        mDateButton = (Button)v.findViewById(R.id.crime_date);
        //mDateButton.setText(dateFormat.format(mCrime.getDate()));
        //mDateButton.setText(mCrime.getDate().toString());
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
                //Makes CrimeFragment the target fragment of the DatePickerFragment instance.
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
            }
        });

        // Gets a reference to the CheckBox and sets a listener that will update the mSolved
        // field of the Crime.
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        // Displays the Crime's solved status.
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Set the crime's solved property
                mCrime.setSolved(isChecked);
            }
        });

        return v;


    } // End onCreateView.

    //Retrieve the extra, set the date on the Crime, and refresh the text of the date button.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_DATE)
        {
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            //mDateButton.setText(mCrime.getDate().toString());
            updateDate();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity()) != null)
                {
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

} // End class CrimeFragment.
