package com.example.michal.mpcriminalintent;


import java.util.Date;
import java.util.UUID;

/**
 * Created by Michal on 10/1/2015.
 */
public class Crime
{
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime()
    {
        // Generate unique identifier
        mId = UUID.randomUUID();

        // Initializing the Date variable using the default Date constructor. This sets mDate to
        // the current date. This will be the default date for a crime.
        mDate = new Date();
    }

    // Called by getView() from the ArrayAdapter to display the crime names in the list view.
    @Override
    public String toString()
    {
        return mTitle;
    }

    public UUID getId()
    {
        return mId;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public void setTitle(String title)
    {
        mTitle = title;
    }

    public Date getDate()
    {
        return mDate;
    }

    public void setDate(Date date)
    {
        mDate = date;
    }

    public boolean isSolved()
    {
        return mSolved;
    }

    public void setSolved(boolean solved)
    {
        mSolved = solved;
    }
} // End class Crime.
