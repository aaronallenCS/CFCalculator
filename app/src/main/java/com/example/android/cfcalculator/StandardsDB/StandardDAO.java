package com.example.android.cfcalculator.StandardsDB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface StandardDAO
{
    @Insert
    public void insertStandard(Standard s);

    @Query("SELECT mId, NameOfMeasurement,  result_feet, result_inches FROM standards")
    LiveData<List<Standard>> getAllStandard();

    @Query("SELECT result_feet FROM standards")
    public int returnResultFeet();

    @Query("SELECT result_inches FROM standards")
    public int returnResultInches();
}
