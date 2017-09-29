package com.jamstudio.umbrella.api;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jamstudio.umbrella.model.CurrentObservation;

import com.jamstudio.umbrella.model.WeatherData;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Converter.Factory.*;


/**
 * Created by muzzi on 9/17/17.
 */

public class APIServiceProvider
{
    public static Retrofit getRetrofitBuilder()
    {

        return new Retrofit.Builder()
                .baseUrl("http://api.wunderground.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
