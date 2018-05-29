package com.interview.guru.gallery.helpers;

import com.interview.guru.gallery.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private  String BaseUrl;

    public RetrofitHelper(String baseUrl) {
        BaseUrl = baseUrl;
    }




   public  Retrofit getRetrofit()
    {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(BaseUrl)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        ).build();
        return retrofit;
    }
}
