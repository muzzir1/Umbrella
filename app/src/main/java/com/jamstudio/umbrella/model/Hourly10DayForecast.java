
package com.jamstudio.umbrella.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hourly10DayForecast {

    @SerializedName("hourly_forecast")
    @Expose
    private List<HourlyForecast> hourlyForecast = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Hourly10DayForecast() {
    }

    /**
     * 
     * @param hourlyForecast
     */
    public Hourly10DayForecast(List<HourlyForecast> hourlyForecast) {
        super();
        this.hourlyForecast = hourlyForecast;
    }

    public List<HourlyForecast> getHourlyForecast() {
        return hourlyForecast;
    }

    public void setHourlyForecast(List<HourlyForecast> hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

}
