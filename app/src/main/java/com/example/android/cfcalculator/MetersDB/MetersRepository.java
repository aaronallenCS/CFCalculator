package com.example.android.cfcalculator.MetersDB;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.android.cfcalculator.MeasureRepository;
import com.example.android.cfcalculator.RoomDBClasses.MeasurementDatabase;

import java.util.List;


//meters repository
public class MetersRepository
{
    private MetersDAO metersDAO;
    private LiveData<List<Meters>> mAllMeters;

    public MetersRepository(Application application)
    {
        MeasurementDatabase db = MeasurementDatabase.getDatabase(application);
        metersDAO = db.metersDAO();
        mAllMeters =  metersDAO.getAllMeters();
    }

    LiveData<List<Meters>> getAllMeters()
    {
        return mAllMeters;
    }

    public void insert(Meters m)
    {
        new insertAsyncTask(metersDAO).execute(m);
    }

    private static class insertAsyncTask extends AsyncTask<Meters, Void, Void>
    {
        private MetersDAO mAsyncTaskDao;

        insertAsyncTask(MetersDAO dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Meters... params)
        {
            mAsyncTaskDao.insertMeters(params[0]);
            return null;
        }
    }
}
