package com.stebakov.limetv.data.core;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Channels {
    @SerializedName("channels")
    @Expose
    private ArrayList<Channel> channels = null;

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<Channel> channels) { this.channels = channels; }
}
