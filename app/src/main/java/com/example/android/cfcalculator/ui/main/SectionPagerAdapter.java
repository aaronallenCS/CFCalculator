package com.example.android.cfcalculator.ui.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionPagerAdapter extends FragmentPagerAdapter
{

    private Context mContext;

    public SectionPagerAdapter(Context context, FragmentManager fm)
    {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position)
    {
        if(position == 0)
        {
            return new StandardFragment();
        }
        else
        {
            return new MetersFragment();
        }
    }

    @Override
    public int getCount()
    {
        return 2;
    }

}
