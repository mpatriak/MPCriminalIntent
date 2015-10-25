package com.example.michal.mpcriminalintent;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Michal on 10/1/2015.
 */
public class Crime
{
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_DATE = "date";

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

    // Constructor that accepts a JSON object.
    public Crime(JSONObject json) throws JSONException
    {
        mId = UUID.fromString(json.getString(JSON_ID));
        if (json.has(JSON_TITLE))
        {
            mTitle = json.getString(JSON_TITLE);
        }
        mSolved = json.getBoolean(JSON_SOLVED);
        mDate = new Date(json.getLong(JSON_DATE));
    }

    // Implements toJSON() to save a Crime in JSON format and return an instance of the class
    // JSONObject that can be put in a JSONArray.
    public JSONObject toJSON() throws JSONException
    {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_TITLE, mTitle);
        json.put(JSON_SOLVED, mSolved);
        json.put(JSON_DATE, mDate.getTime());
        return json;
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
