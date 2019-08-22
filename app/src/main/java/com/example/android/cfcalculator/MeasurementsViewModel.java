package com.example.android.cfcalculator;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.cfcalculator.RoomDBClasses.MeasurementDAO;
import com.example.android.cfcalculator.RoomDBClasses.MeasurementDatabase;
import com.example.android.cfcalculator.RoomDBClasses.Measurements;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MeasurementsViewModel extends AndroidViewModel
{
    private MeasureRepository measureRepository;

    private LiveData<List<Measurements>> mAllMeasures;

    public MeasurementsViewModel(Application application)
    {
        super(application);

        measureRepository = new MeasureRepository(application);
        mAllMeasures = measureRepository.getAllWords();
    }

    public LiveData<List<Measurements>> getFtInMeasurements()
    {
        return mAllMeasures;
    }

    public void insert(Measurements measure)
    {
        measureRepository.insert(measure);
    }
}
