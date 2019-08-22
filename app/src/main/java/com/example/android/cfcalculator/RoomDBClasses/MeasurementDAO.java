package com.example.android.cfcalculator.RoomDBClasses;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface MeasurementDAO
{
    @Insert
    public void insertMeasurements(Measurements m);

    @Query("SELECT mId, result_ft, result_in, result_m, result_cm FROM measurements")
    LiveData<List<Measurements>> getAllMeasurements();


    @Query("SELECT result_ft FROM measurements")
    public int returnResultFt();

    @Query("SELECT result_in FROM measurements")
    public int returnResultIn();

    @Query("SELECT result_m FROM measurements")
    public int returnResultM();

    @Query("SELECT result_cm FROM measurements")
    public int returnResultCM();

}
