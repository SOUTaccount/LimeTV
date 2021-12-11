package com.stebakov.limetv.data.core;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Current {
    @SerializedName("timestart")
    @Expose
    private Integer timestart;
    @SerializedName("timestop")
    @Expose
    private Integer timestop;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("cdnvideo")
    @Expose
    private Integer cdnvideo;
    @SerializedName("rating")
    @Expose
    private Integer rating;

    public Integer getTimestart() {
        return timestart;
    }

    public void setTimestart(Integer timestart) {
        this.timestart = timestart;
    }

    public Integer getTimestop() {
        return timestop;
    }

    public void setTimestop(Integer timestop) {
        this.timestop = timestop;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCdnvideo() {
        return cdnvideo;
    }

    public void setCdnvideo(Integer cdnvideo) {
        this.cdnvideo = cdnvideo;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
