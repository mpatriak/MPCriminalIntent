package com.example.michal.mpcriminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Created by Michal on 10/1/2015.
 */
public class CrimeFragment extends Fragment
{
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private java.text.DateFormat dateFormat;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
        dateFormat = android.text.format.DateFormat.getLongDateFormat(this.getActivity());
    }

    // Inflates fragment_crime.xml
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        // Passes the layout resource ID
        // False parameter means fragment will be inflated in code.
        View v = inflater.inflate(R.layout.fragment_crime, parent, false);

        // Gets a reference to the EditText and adds a listener.
        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher()
        {
            // This method returns a string, which will be used to set the Crime's title.
            public void onTextChanged(CharSequence c, int start, int before, int count)
            {
                mCrime.setTitle(c.toString());
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after)
            {
                // This space intentionally left blank.
            }

            public void afterTextChanged(Editable c)
            {
                // This space intentionally left blank.
            }
        });

        // Gets a reference to the new button, sets its text as the date of the crime, and disabled
        // it for now.
        mDateButton = (Button)v.findViewById(R.id.crime_date);
        mDateButton.setText(dateFormat.format(mCrime.getDate()));
        //mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);

        // Gets a reference to the CheckBox and sets a listener that will update the mSolved
        // field of the Crime.
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
           {
               // Set the crime's solved property
               mCrime.setSolved(isChecked);
           }
        });

        return v;



    } // End onCreate.
} // End class CrimeFragment.
