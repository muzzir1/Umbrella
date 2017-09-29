package com.jamstudio.umbrella.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by muzzi on 9/17/17.
 */

public class WeatherData
{

    @SerializedName("current_observation")
    @Expose
    private CurrentObservation currentObservation;

    /**
     * No args constructor for use in serialization
     *
     */
    public WeatherData() {
    }

    /**
     *
     * @param currentObservation
     */

    public WeatherData(CurrentObservation currentObservation)
    {
        //super();
        this.currentObservation = currentObservation;
    }

    public CurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(CurrentObservation currentObservation) {
        this.currentObservation = currentObservation;
    }

}
