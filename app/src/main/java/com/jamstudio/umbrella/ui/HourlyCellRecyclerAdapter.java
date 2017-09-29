package com.jamstudio.umbrella.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jamstudio.umbrella.R;
import com.jamstudio.umbrella.model.HourlyForecast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import static com.jamstudio.umbrella.R.color.weather_cool;
import static com.jamstudio.umbrella.R.color.weather_warm;

/**
 * Created by muzzi on 9/20/17.
 */

 public class HourlyCellRecyclerAdapter extends RecyclerView.Adapter<HourlyCellRecyclerAdapter.MyViewHolder>
{
    private List<HourlyForecast> hourlyForecastList;
    private List<String> timeList = new ArrayList<>();
    private List<String> tempList = new ArrayList<>();
    private List<String> iconList = new ArrayList<>();
    private int mintemp = 0;
    private int maxtemp = 0;
    private int minTempCount = 0;
    private int maxTempCount = 0;
    private int temp;
    private HourlyForecast hourlyForecast1;
    Context context;

    public HourlyCellRecyclerAdapter(Context context, List<HourlyForecast> hourlyForecastList, String weekdayName)
    {
        this.hourlyForecastList = hourlyForecastList;
        this.context = context;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String unitsPreference = sharedPreferences.getString("units",null);

        for(int i = 0; i < hourlyForecastList.size();i++)
        {
            String name = hourlyForecastList.get(i).getFCTTIME().getWeekdayName();

            if(name.equals(weekdayName))
            {
                timeList.add(hourlyForecastList.get(i).getFCTTIME().getCivil());


                if("0".equals(unitsPreference))
                {
                    temp = Integer.parseInt(hourlyForecastList.get(i).getTemp().getEnglish());
                    tempList.add(temp+"");

                    setMinMaxTemprature();

                }
                else if("1".equals(unitsPreference))
                {
                    temp = Integer.parseInt(hourlyForecastList.get(i).getTemp().getMetric());
                    tempList.add(temp+"");

                    setMinMaxTemprature();
                }
                else
                {
                    temp = Integer.parseInt(hourlyForecastList.get(i).getTemp().getEnglish());
                    tempList.add(temp+"");

                    setMinMaxTemprature();
                }

                iconList.add(hourlyForecastList.get(i).getIcon());
            }

        }

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView weatherIcon;
        private TextView hourlyTime;
        private TextView hourlyTemp;

        public MyViewHolder(View itemView)
        {
            super(itemView);

            weatherIcon = (ImageView) itemView.findViewById(R.id.weather_icon);
            hourlyTime = (TextView) itemView.findViewById(R.id.hourly_Time_TextView);
            hourlyTemp = (TextView) itemView.findViewById(R.id.hourly_Temp_TextView);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_cell_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.hourlyTime.setText(timeList.get(position));

        holder.hourlyTemp.setText(tempList.get(position) + "\u00b0");

        if (Integer.parseInt(tempList.get(position)) == (mintemp)) {
            if (minTempCount == 0) {
                holder.hourlyTime.setTextColor(context.getResources().getColor(weather_cool));
                holder.hourlyTemp.setTextColor(context.getResources().getColor(weather_cool));
                holder.weatherIcon.setColorFilter(context.getResources().getColor(weather_cool));
                minTempCount++;
            }
        }
        if (Integer.parseInt(tempList.get(position)) == (maxtemp)) {
            if (maxTempCount == 0) {
                holder.hourlyTime.setTextColor(context.getResources().getColor(weather_warm));
                holder.hourlyTemp.setTextColor(context.getResources().getColor(weather_warm));
                holder.weatherIcon.setColorFilter(context.getResources().getColor(weather_warm));
                maxTempCount++;
            }
        }


        switch (iconList.get(position))
        {
            case "cloudy":
                holder.weatherIcon.setImageResource(R.drawable.weather_cloudy);
                break;
            case "fog":
                holder.weatherIcon.setImageResource(R.drawable.weather_fog);
                break;
            case "hail":
                holder.weatherIcon.setImageResource(R.drawable.weather_hail);
                break;
            case "lightning":
                holder.weatherIcon.setImageResource(R.drawable.weather_lightning);
                break;
            case "lightningrainy":
                holder.weatherIcon.setImageResource(R.drawable.weather_lightning_rainy);
                break;
            case "partlycloudy":
                holder.weatherIcon.setImageResource(R.drawable.weather_partlycloudy);
                break;
            case "rainy":
                holder.weatherIcon.setImageResource(R.drawable.weather_rainy);
                break;
            case "snowy":
                holder.weatherIcon.setImageResource(R.drawable.weather_snowy);
                break;
            case "sunny":
                holder.weatherIcon.setImageResource(R.drawable.weather_sunny);
                break;
            case "windyvariant":
                holder.weatherIcon.setImageResource(R.drawable.weather_windy_variant);
                break;
            default:
                holder.weatherIcon.setImageResource(R.drawable.weather_sunny);
                break;
        }

    }

    public void setMinMaxTemprature()
    {
        if(mintemp == 0 && maxtemp == 0) {
            maxtemp = temp;
            mintemp = temp;
        }
        else {
            if (temp < mintemp) {
                mintemp = temp;
            } else if (temp > maxtemp) {
                maxtemp = temp;
            }
        }
    }


    @Override
    public int getItemCount() {
        return timeList.size();
    }
}
