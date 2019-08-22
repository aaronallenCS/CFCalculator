package com.example.android.cfcalculator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.android.cfcalculator.RoomDBClasses.Measurements;

import java.util.List;

public class MeasurementAdapter extends RecyclerView.Adapter<MeasurementAdapter.MeasurementsViewHolder>
{
    private final LayoutInflater mInflater;
    private List<Measurements> mMeasures;

    public MeasurementAdapter(Context context)
    {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MeasurementsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = mInflater.inflate(R.layout.measurement_item, parent, false);

        return new MeasurementsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MeasurementsViewHolder holder, int position)
    {
        if(mMeasures != null)
        {
            Measurements current = mMeasures.get(position);
            holder.name.setText(current.getName());
            holder.feetResult.setText(current.getFinalResultFt());
            holder.inchesResult.setText(current.getFinalResultIn());
            if(holder.metersResult == null)
            {
            }
            else
            {
                holder.metersResult.setText(current.getFinalResultM());
                holder.centimetersResult.setText(current.getFinalResultCM());
            }
        }
        else
        {
            holder.feetResult.setText("Feet result");
            holder.inchesResult.setText("Inches result");
            holder.metersResult.setText("Meters result");
            holder.centimetersResult.setText("Centimeters result");
        }
    }

    public void setMeasures(List<Measurements> measures)
    {
        mMeasures = measures;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        if(mMeasures != null)
            return mMeasures.size();
        else return 0;
    }



    public class MeasurementsViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView name;
        private final TextView feetResult;
        private final TextView inchesResult;

        //meters information
        private final TextView nameMeters;
        private final TextView metersResult;
        private final TextView centimetersResult;

        private MeasurementsViewHolder(View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.name_of_measure);
            feetResult = itemView.findViewById(R.id.item_feet_result);
            inchesResult = itemView.findViewById(R.id.item_inches_result);

            nameMeters = itemView.findViewById(R.id.name_of_measure_m);
            metersResult = itemView.findViewById(R.id.meters_result);
            centimetersResult = itemView.findViewById(R.id.centimeters_result);
        }

    }
}
