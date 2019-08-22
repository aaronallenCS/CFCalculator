package com.example.android.cfcalculator.RoomDBClasses;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "measurements")
public class Measurements
{
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mId")
    public int mId;
    @ColumnInfo(name="result_in")
    public String finalResultIn;

    @ColumnInfo(name="result_ft")
    public String finalResultFt;

    @ColumnInfo(name="result_m")
    public String finalResultM;

    @ColumnInfo(name="result_cm")
    public String finalResultCM;

    @ColumnInfo(name="NameMeasure")
    public String measureName;

    @ColumnInfo(name="measureNameMeters")
    public String measureNameMeters;

    public Measurements(String measureName, String measureNameMeters, String finalResultFt, String finalResultIn,
                        String finalResultM, String finalResultCM)
    {

        this.measureName = measureName;
        this.finalResultFt = finalResultFt;
        this.finalResultIn = finalResultIn;

        this.measureNameMeters = measureNameMeters;
        this.finalResultM = finalResultM;
        this.finalResultCM = finalResultCM;

    }

    public void setName(String measureName)
    {
        this.measureName = measureName;
    }
    public String getName()
    {
        return measureName;
    }

    public void setNameMeters(String measureNameMeters){this.measureNameMeters = measureNameMeters;}
    public String getNameMeters() { return measureNameMeters; }

    public void setFinalResultFt(String finalResultFt)
    {
        this.finalResultFt = finalResultFt;
    }
    public String getFinalResultFt()
    {
        return finalResultFt;
    }

    public void setFinalResultIn(String finalResultIn)
    {
        this.finalResultIn = finalResultIn;
    }
    public String getFinalResultIn() { return finalResultIn; }

    public void setFinalResultM(String finalResultM)
    {
        this.finalResultM = finalResultM;
    }
    public String getFinalResultM()
    {
        return finalResultM;
    }

    public void setFinalResultCM(String finalResultCM)
    {
        this.finalResultCM = finalResultCM;
    }
    public String getFinalResultCM()
    {
        return finalResultCM;
    }

}
