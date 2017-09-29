package com.jamstudio.umbrella.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jamstudio.umbrella.R;
import com.jamstudio.umbrella.api.APIServiceProvider;
import com.jamstudio.umbrella.api.RetrofitWeatherService;
import com.jamstudio.umbrella.model.CurrentObservation;
import com.jamstudio.umbrella.model.WeatherData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private TextView main_current_temprature, main_current_weather;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.main_appbar);
        main_current_temprature = (TextView) findViewById(R.id.main_current_temprature);
        main_current_weather =(TextView) findViewById(R.id.main_current_weather);

        toolbar.inflateMenu(R.menu.menu_items);
        setSupportActionBar(toolbar);

        RetrofitTask();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_items, menu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onOptionsItemSelected(item);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.settings_item:
                //Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }

    public void RetrofitTask()
    {
        RetrofitWeatherService rf_service = APIServiceProvider.getRetrofitBuilder().create(RetrofitWeatherService.class);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String unitsPreference = sharedPreferences.getString("units",null);
        String zipPreference = sharedPreferences.getString("zip_code", null);

        if(zipPreference == null)
        {
            zipPreference = "95051";
        }

        Call<WeatherData> call = rf_service.currentZipCallable(zipPreference);

        call.enqueue(new Callback<WeatherData>()
        {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response)
            {

                WeatherData weatherData = response.body();

                if(weatherData.getCurrentObservation() != null) {

                    toolbar.setTitle(weatherData.getCurrentObservation().getDisplayLocation().getFull());


                    if("0".equals(unitsPreference))
                    {
                        String tempFormater = "" + weatherData.getCurrentObservation().getTempF();
                        String temp = tempFormater.substring(0, tempFormater.indexOf("."));
                        main_current_temprature.setText(temp + "\u00b0");
                        setAppBarToolbarColor(Integer.parseInt(temp));
                    }
                    else if("1".equals(unitsPreference))
                    {
                        String tempFormater = "" + weatherData.getCurrentObservation().getTempC();
                        String temp = tempFormater.substring(0, tempFormater.indexOf("."));
                        main_current_temprature.setText(temp + "\u00b0");
                        setAppBarToolbarColor(Integer.parseInt(temp));
                    }
                    else
                    {
                        String tempFormater = "" + weatherData.getCurrentObservation().getTempF();
                        String temp = tempFormater.substring(0, tempFormater.indexOf("."));
                        main_current_temprature.setText(temp + "\u00b0");
                        setAppBarToolbarColor(Integer.parseInt(temp));
                    }


                    main_current_weather.setText(weatherData.getCurrentObservation().getWeather());


                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable throwable)
            {

            }
        });
    }

    public void setAppBarToolbarColor(int temp)
    {
        if(temp < 60)
        {
            toolbar.setBackgroundColor(getResources().getColor(R.color.weather_cool));
            appBarLayout.setBackgroundColor(getResources().getColor(R.color.weather_cool));
        }
        else if(temp >= 60)
        {
            toolbar.setBackgroundColor(getResources().getColor(R.color.weather_warm));
            appBarLayout.setBackgroundColor(getResources().getColor(R.color.weather_warm));
        }
    }


    @Override
    protected void onResume()
    {
        super.onResume();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        RetrofitTask();
    }
}
