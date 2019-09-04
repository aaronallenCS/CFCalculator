package com.example.android.cfcalculator.MetersDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "meters")
public class Meters
{
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="mId")
    public int mId;

    @ColumnInfo(name="meters_result")
    public String resultMeters;

    @ColumnInfo(name="centimeters_result")
    public String resultCentimeters;

    @ColumnInfo(name="NameOfMeasurement")
    public String measureName;

    public Meters(String measureName, String resultMeters, String resultCentimeters)
    {
        this.measureName = measureName;
        this.resultMeters = resultMeters;
        this.resultCentimeters = resultCentimeters;
    }

    public void setName(String measureName)
    {
        this.measureName = measureName;
    }
    public String getName()
    {
        return measureName;
    }

    public void setMetersResult(String resultMeters)
    {
        this.resultMeters = resultMeters;
    }
    public String getMetersResult()
    {
        return resultMeters;
    }

    public void setCentimetersResult(String resultCentimeters)
    {
        this.resultCentimeters = resultCentimeters;
    }

    public String getCentimetersResult()
    {
        return resultCentimeters;
    }
}
