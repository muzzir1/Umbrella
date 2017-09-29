package com.jamstudio.umbrella.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by muzzi on 9/17/17.
 */

public class CurrentObservation
{


    @SerializedName("display_location")
    @Expose
    private DisplayLocation displayLocation;
    @SerializedName("weather")
    @Expose
    private String weather;
    @SerializedName("temp_f")
    @Expose
    private Double tempF;
    @SerializedName("temp_c")
    @Expose
    private Double tempC;

    /**
     * No args constructor for use in serialization
     *
     */
    public CurrentObservation() {
    }

    /**
     *
     * @param tempC
     * @param displayLocation
     * @param weather
     * @param tempF
     */
    public CurrentObservation(DisplayLocation displayLocation, String weather, Double tempF, Double tempC) {
        super();
        this.displayLocation = displayLocation;
        this.weather = weather;
        this.tempF = tempF;
        this.tempC = tempC;
    }

    public DisplayLocation getDisplayLocation() {
        return displayLocation;
    }

    public void setDisplayLocation(DisplayLocation displayLocation) {
        this.displayLocation = displayLocation;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Double getTempF() {
        return tempF;
    }

    public void setTempF(Double tempF) {
        this.tempF = tempF;
    }

    public Double getTempC() {
        return tempC;
    }

    public void setTempC(Double tempC) {
        this.tempC = tempC;
    }

}

