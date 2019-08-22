package com.example.android.cfcalculator.RoomDBClasses;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import com.example.android.cfcalculator.DateConverter;

@Database(entities = {Measurements.class}, version = 12)
@TypeConverters({DateConverter.class})

public abstract class MeasurementDatabase extends RoomDatabase
{
    private static final String DB_NAME = "measurement_db";
    private static MeasurementDatabase instance;

    public static synchronized  MeasurementDatabase getDatabase(final Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(), MeasurementDatabase.class,
                    DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


    public abstract MeasurementDAO measurementDAO();
}
