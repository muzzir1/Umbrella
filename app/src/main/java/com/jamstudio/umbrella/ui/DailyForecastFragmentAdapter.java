package com.jamstudio.umbrella.ui;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jamstudio.umbrella.R;
import com.jamstudio.umbrella.model.HourlyForecast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by muzzi on 9/20/17.
 */

public class DailyForecastFragmentAdapter extends RecyclerView.Adapter<DailyForecastFragmentAdapter.DailyForecastFragmentViewHolder>
{
    private List<HourlyForecast> hourlyForecastList;
    private List<String> daylist = new ArrayList<>();
    private HashSet<String> dayset = new LinkedHashSet<>(daylist);
    private int counter = 0;
    private Context context;

    public DailyForecastFragmentAdapter(Context context, List<HourlyForecast> hourlyForecastList)
    {
        this.hourlyForecastList = hourlyForecastList;
        this.context = context;

        if(hourlyForecastList != null)
        {
            for (int i = 0; i < hourlyForecastList.size(); i++) {
                daylist.add(hourlyForecastList.get(i).getFCTTIME().getWeekdayName());

            }
        }
        else
        {
            Toast.makeText(context, "Invalid ZipCode", Toast.LENGTH_LONG);
        }

        dayset.addAll(daylist);
        daylist.clear();
        daylist.addAll(dayset);

    }

    public class DailyForecastFragmentViewHolder extends RecyclerView.ViewHolder
    {
        private TextView dayTextView;
        private RecyclerView hourlyCellRecyclerView;
        public DailyForecastFragmentViewHolder(View itemView)
        {
            super(itemView);
            dayTextView = (TextView) itemView.findViewById(R.id.dayTextView);
            hourlyCellRecyclerView = (RecyclerView) itemView.findViewById(R.id.hourly_cell_recycler_view);
            hourlyCellRecyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(), 4));
            hourlyCellRecyclerView.setItemAnimator(new DefaultItemAnimator());

        }

    }

    @Override
    public DailyForecastFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemlayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_card_layout,parent,false);

        return new DailyForecastFragmentViewHolder(itemlayout);
    }

    @Override
    public void onBindViewHolder(DailyForecastFragmentViewHolder holder, int position)
    {
        HourlyForecast hourlyForecast = hourlyForecastList.get(position);

        if(daylist.get(position) == daylist.get(0))
        {
            holder.dayTextView.setText("Today");
            holder.hourlyCellRecyclerView.setAdapter(new HourlyCellRecyclerAdapter(context, hourlyForecastList, daylist.get(0)));

        }
        else if(daylist.get(position) == daylist.get(1))
        {
            holder.dayTextView.setText("Tomorrow");
            holder.hourlyCellRecyclerView.setAdapter(new HourlyCellRecyclerAdapter(context, hourlyForecastList, daylist.get(position)));
        }
        else
        {
            holder.dayTextView.setText(daylist.get(position));
            holder.hourlyCellRecyclerView.setAdapter(new HourlyCellRecyclerAdapter(context, hourlyForecastList, daylist.get(position)));
        }

    }



    @Override
    public int getItemCount() {
        return daylist.size();
    }


}
