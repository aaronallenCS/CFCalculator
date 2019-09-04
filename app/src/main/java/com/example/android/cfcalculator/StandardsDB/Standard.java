package com.example.android.cfcalculator.StandardsDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "standards")
public class Standard
{
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="mId")
    public int mId;

    @ColumnInfo(name="result_feet")
    public String resultFeet;

    @ColumnInfo(name="result_inches")
    public String resultInches;

    @ColumnInfo(name="NameOfMeasurement")
    public String measureName;

    public Standard(String measureName, String resultFeet, String resultInches)
    {
        this.measureName = measureName;
        this.resultFeet = resultFeet;
        this.resultInches = resultInches;
    }

    public void setName(String measureName)
    {
        this.measureName = measureName;
    }
    public String getName()
    {
        return measureName;
    }

    public void setFeet(String resultFeet)
    {
        this.resultFeet = resultFeet;
    }

    public String getFeet()
    {
        return resultFeet;
    }

    public void setInches(String resultInches)
    {
        this.resultInches = resultInches;
    }

    public String getInches()
    {
        return resultInches;
    }
}
