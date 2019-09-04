package com.example.android.cfcalculator;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.android.cfcalculator.RoomDBClasses.MeasurementDatabase;

import java.util.List;

public class MeasureRepository
{
    private MeasurementDAO mMeasureDao;
    private LiveData<List<Measurements>> mAllMeasures;

    public MeasureRepository(Application application)
    {
        MeasurementDatabase db = MeasurementDatabase.getDatabase(application);
        mMeasureDao = db.measurementDAO();
        mAllMeasures = mMeasureDao.getAllMeasurements();
    }

    LiveData<List<Measurements>> getAllMeasures()
    {
        return mAllMeasures;
    }

    public void insert(Measurements m)
    {
        new insertAsyncTask(mMeasureDao).execute(m);
    }

    private static class insertAsyncTask extends AsyncTask<Measurements, Void, Void>
    {
        private MeasurementDAO mAsyncTaskDao;

        insertAsyncTask(MeasurementDAO dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Measurements... params)
        {
            mAsyncTaskDao.insertMeasurements(params[0]);
            return null;
        }
    }
}
