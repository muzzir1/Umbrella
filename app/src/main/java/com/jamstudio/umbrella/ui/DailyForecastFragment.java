package com.jamstudio.umbrella.ui;


import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jamstudio.umbrella.R;
import com.jamstudio.umbrella.api.APIServiceProvider;
import com.jamstudio.umbrella.api.RetrofitWeatherService;
import com.jamstudio.umbrella.model.Hourly10DayForecast;
import com.jamstudio.umbrella.model.HourlyForecast;
import com.jamstudio.umbrella.model.WeatherData;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyForecastFragment extends Fragment {

    private RecyclerView daily_forecast_card_recycler;
    private DailyForecastFragmentAdapter dailyForecastFragmentAdapter;
    private List<HourlyForecast> hourlyForecastList = new ArrayList<>() ;
    private HourlyForecast hourlyForecast;
    private View view;

    public DailyForecastFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view= inflater.inflate(R.layout.daily_forecast_card_fragment,container,false);

        daily_forecast_card_recycler = (RecyclerView) view.findViewById(R.id.main_forecast_cards_recyclerview);
        daily_forecast_card_recycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL,false));
        dailyForecastFragmentAdapter = new DailyForecastFragmentAdapter(view.getContext(),hourlyForecastList);
        daily_forecast_card_recycler.setAdapter(dailyForecastFragmentAdapter);
        daily_forecast_card_recycler.setNestedScrollingEnabled(false);

        RetrofitTask();

        return view;
    }

    public void RetrofitTask()
    {
        RetrofitWeatherService rf_service = APIServiceProvider.getRetrofitBuilder().create(RetrofitWeatherService.class);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        String zipPreference = sharedPreferences.getString("zip_code", null);

        if(zipPreference == null)
        {
            zipPreference = "95051";
        }

        Call<Hourly10DayForecast> call = rf_service.ForecastZipCallable(zipPreference);

        call.enqueue(new Callback<Hourly10DayForecast>() {
            @Override
            public void onResponse(Call<Hourly10DayForecast> call, Response<Hourly10DayForecast> response)
            {
                Hourly10DayForecast hourly10DayForecast = response.body();

                hourlyForecastList = hourly10DayForecast.getHourlyForecast();

                daily_forecast_card_recycler.setAdapter(new DailyForecastFragmentAdapter(view.getContext(), hourlyForecastList));
                dailyForecastFragmentAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Hourly10DayForecast> call, Throwable throwable)
            {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        RetrofitTask();
    }
}
