
package com.aniruddhsingh.weathernow.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Clouds {

    @SerializedName("all")
    @Expose
    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("all", all).toString();
    }

}
