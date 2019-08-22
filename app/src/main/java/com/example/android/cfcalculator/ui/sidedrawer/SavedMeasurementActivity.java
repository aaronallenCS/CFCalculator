package com.example.android.cfcalculator.ui.sidedrawer;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.cfcalculator.MeasurementAdapter;
import com.example.android.cfcalculator.MeasurementsViewModel;
import com.example.android.cfcalculator.R;
import com.example.android.cfcalculator.RoomDBClasses.MeasurementDatabase;
import com.example.android.cfcalculator.RoomDBClasses.Measurements;

import java.util.List;

public class SavedMeasurementActivity extends AppCompatActivity
{
    private MeasurementsViewModel measurementsViewModel;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_measurement);

        RecyclerView recyclerView = findViewById(R.id.saved_recycler);
        final MeasurementAdapter adapter = new MeasurementAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        measurementsViewModel = ViewModelProviders.of(this).get(MeasurementsViewModel.class);

        measurementsViewModel.getFtInMeasurements().observe(this, new Observer<List<Measurements>>(){
            @Override
            public void onChanged(@Nullable final List<Measurements> measures)
            {
                adapter.setMeasures(measures);
            }
        });

    }
}
