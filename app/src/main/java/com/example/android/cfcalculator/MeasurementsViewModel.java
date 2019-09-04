package com.example.android.cfcalculator;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class MeasurementsViewModel extends AndroidViewModel
{

    private MeasureRepository measureRepository;

//    private MeasureRepository measureRepository;
//
//    private LiveData<List<Measurements>> mAllMeasures;
//
//    public MeasurementsViewModel(Application application)
//    {
//        super(application);
//
//        measureRepository = new MeasureRepository(application);
//        mAllMeasures = measureRepository.getAllMeasures();
//    }
//
//    public LiveData<List<Measurements>> getFtInMeasurements()
//    {
//        return mAllMeasures;
//    }
//
//    public void insert(Measurements measure)
//    {
//        measureRepository.insert(measure);
//    }
}
