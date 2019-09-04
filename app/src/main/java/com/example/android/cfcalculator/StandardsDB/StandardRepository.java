package com.example.android.cfcalculator.StandardsDB;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.android.cfcalculator.RoomDBClasses.MeasurementDatabase;

import java.util.List;

//standard repository
public class StandardRepository
{
    private StandardDAO standardDAO;
    private LiveData<List<Standard>> mAllStandard;

    public StandardRepository(Application application)
    {
        MeasurementDatabase db = MeasurementDatabase.getDatabase(application);
        standardDAO = db.standardDAO();
        mAllStandard = standardDAO.getAllStandard();
    }

    LiveData<List<Standard>> getAllStandard()
    {
        return mAllStandard;
    }

    private static class insertAsyncTask extends AsyncTask<Standard, Void, Void>
    {
        private StandardDAO mAsyncTaskDao;

        insertAsyncTask(StandardDAO dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Standard... params)
        {
            mAsyncTaskDao.insertStandard(params[0]);
            return null;
        }
    }
}
