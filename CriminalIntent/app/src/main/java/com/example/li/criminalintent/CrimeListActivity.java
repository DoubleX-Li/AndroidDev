package com.example.li.criminalintent;

import android.support.v4.app.Fragment;

import layout.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
