package com.stebakov.limetv.data.core;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface JSONPlaceHolder {
    @Headers("Content-Type: application/json")
    @GET("playlist/")
    Call<Channels> getChannels();
}
