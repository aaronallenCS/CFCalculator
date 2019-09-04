package com.example.android.cfcalculator.MetersDB;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.android.cfcalculator.StandardsDB.Standard;

import java.util.List;

public class MetersViewModel extends AndroidViewModel
{

    private MetersRepository metersRepository;

    private LiveData<List<Meters>> mAllMeters;

    public MetersViewModel(Application application)
    {
        super(application);

        metersRepository = new MetersRepository(application);
        mAllMeters = metersRepository.getAllMeters();
    }

    public LiveData<List<Meters>> getMeters()
    {
        return mAllMeters;
    }

    public void insert(Meters meters)
    {
        metersRepository.insert(meters);
    }


}
