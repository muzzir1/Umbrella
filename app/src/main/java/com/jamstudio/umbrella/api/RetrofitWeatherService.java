package com.jamstudio.umbrella.api;


import com.jamstudio.umbrella.model.Hourly10DayForecast;
import com.jamstudio.umbrella.model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.jamstudio.umbrella.api.RetrofitWeatherService.API_KEY;


/**
 * Created by muzzi on 9/17/17.
 */

public interface RetrofitWeatherService
{
    String API_KEY =  "0827312c4090b235";

    @GET("/api/"+API_KEY+"/conditions/q/{zip}.json" )
    Call<WeatherData> currentZipCallable(@Path("zip") String zipCode);

    @GET("/api/"+ API_KEY +"/hourly/q/CA/{zip}.json")
    Call<Hourly10DayForecast> ForecastZipCallable(@Path("zip") String zipCode);


}
