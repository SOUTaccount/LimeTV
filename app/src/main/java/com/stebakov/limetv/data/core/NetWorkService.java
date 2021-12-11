package com.stebakov.limetv.data.core;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkService {
    private static NetWorkService mInstance;
    private static final String BASE_URL = "https://limehd.online/";
    private static Retrofit mRetrofit;

    private NetWorkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetWorkService getInstance() {
        if (mInstance==null)
        {
            mInstance = new NetWorkService();
        }
        return mInstance;
    }
    public JSONPlaceHolder getJSONApi ()
    {
        return mRetrofit.create(JSONPlaceHolder.class);
    }
}