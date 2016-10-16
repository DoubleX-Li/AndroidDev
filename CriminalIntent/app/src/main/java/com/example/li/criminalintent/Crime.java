package com.example.li.criminalintent;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by Li on 2016/10/14 0014.
 */

public class Crime {

    private UUID mId;
    private String mTitle;
    private Calendar mCalendar;
    private boolean mSolved;

    public Crime() {
        mId = UUID.randomUUID();
        mCalendar = Calendar.getInstance();
    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Calendar getmCalendar() {
        return mCalendar;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    @Override
    public String toString() {
        return getmTitle();
    }
}
