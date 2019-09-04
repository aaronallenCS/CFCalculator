package com.example.android.cfcalculator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.SavedViewHolder>
{
    private List<String> ftMeasurements;

    public static class SavedViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textView;
        public SavedViewHolder(TextView v)
        {
            super(v);
            textView = v;
        }
    }

    public SavedAdapter(List<String> myDataset)
    {
        ftMeasurements = myDataset;
    }

    @Override
    public SavedAdapter.SavedViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_measurement_item, parent, false);

        SavedViewHolder vh = new SavedViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SavedViewHolder holder, int position)
    {
        holder.textView.setText(ftMeasurements.get(position));

    }

    @Override
    public int getItemCount()
    {
        return ftMeasurements.size();
    }
}
