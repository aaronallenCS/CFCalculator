package com.example.android.cfcalculator.MetersDB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MetersDAO
{
    @Insert
    public void insertMeters(Meters m);

    @Query("SELECT meters_result FROM meters")
    public int metersResult();

    @Query("SELECT centimeters_result FROM meters")
    public int centimetersResult();

    @Query("SELECT mId, NameOfMeasurement, meters_result, centimeters_result FROM meters")
    LiveData<List<Meters>> getAllMeters();
}
