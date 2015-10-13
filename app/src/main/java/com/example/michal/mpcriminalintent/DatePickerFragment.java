package com.example.michal.mpcriminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Michal on 10/11/2015.
 */
public class DatePickerFragment extends DialogFragment
{
    public static final String EXTRA_DATE = "com.example.michal.mpcriminalintent";

    private Date mDate;

    // Date is stored in DatePickerFragment's argument bundle so the DatePickerFragment can
    // access it.
    public static DatePickerFragment newInstance(Date date)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    // Private method that creates an Intent, puts the date on it as an extra, and then calls
    // CrimeFragment.onActivityResult().
    private void sendResult(int resultCode)
    {
        if (getTargetFragment() == null)
            return;

        Intent i = new Intent();
        i.putExtra(EXTRA_DATE, mDate);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        mDate = (Date)getArguments().getSerializable(EXTRA_DATE);

        // Create a calendar to get the year, month, and day.
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);

        DatePicker datePicker = (DatePicker)v.findViewById(R.id.dialog_date_datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                // Translate year, month, day into a Date object using a calendar.
                mDate = new GregorianCalendar(year, month, day).getTime();

                // Update argument to preserve selected value on rotation.
                getArguments().putSerializable(EXTRA_DATE, mDate);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                // Sets an implementation of DialogInterface.OnClickListener that calls the
                // private method and passes in a result code.
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .create();
    }
} // End class DatePickerFragment
