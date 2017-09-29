
package com.jamstudio.umbrella.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HourlyForecast {

    @SerializedName("FCTTIME")
    @Expose
    private FCTTIME fCTTIME;
    @SerializedName("temp")
    @Expose
    private Temp temp;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("icon")
    @Expose
    private String icon;

    /**
     * No args constructor for use in serialization
     * 
     */
    public HourlyForecast() {
    }

    /**
     * 
     * @param icon
     * @param condition
     * @param temp
     * @param fCTTIME
     */
    public HourlyForecast(FCTTIME fCTTIME, Temp temp, String condition, String icon) {
        super();
        this.fCTTIME = fCTTIME;
        this.temp = temp;
        this.condition = condition;
        this.icon = icon;
    }

    public FCTTIME getFCTTIME() {
        return fCTTIME;
    }

    public void setFCTTIME(FCTTIME fCTTIME) {
        this.fCTTIME = fCTTIME;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
