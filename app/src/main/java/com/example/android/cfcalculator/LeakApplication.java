package com.example.android.cfcalculator;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class LeakApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate(); 

        if(LeakCanary.isInAnalyzerProcess(this))
        {
            return;
        }
        LeakCanary.install(this);
    }
}
