package com.jamstudio.umbrella.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by muzzi on 9/17/17.
 */

public class DisplayLocation
{

    @SerializedName("full")
    @Expose
    private String full;

    /**
     * No args constructor for use in serialization
     *
     */
    public DisplayLocation() {
    }

    /**
     *
     * @param full
     */
    public DisplayLocation(String full) {
        super();
        this.full = full;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }
}
