
package com.jamstudio.umbrella.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FCTTIME {

    @SerializedName("civil")
    @Expose
    private String civil;
    @SerializedName("weekday_name")
    @Expose
    private String weekdayName;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FCTTIME() {
    }

    /**
     * 
     * @param civil
     * @param weekdayName
     */
    public FCTTIME(String civil, String weekdayName) {
        super();
        this.civil = civil;
        this.weekdayName = weekdayName;
    }

    public String getCivil() {
        return civil;
    }

    public void setCivil(String civil) {
        this.civil = civil;
    }

    public String getWeekdayName() {
        return weekdayName;
    }

    public void setWeekdayName(String weekdayName) {
        this.weekdayName = weekdayName;
    }

}
