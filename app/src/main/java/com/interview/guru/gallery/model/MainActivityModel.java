package com.interview.guru.gallery.model;

import android.content.Context;
import android.util.Log;

import com.interview.guru.gallery.Constants;
import com.interview.guru.gallery.helpers.RetrofitHelper;
import com.interview.guru.gallery.interfaces.RequestsClient;
import com.interview.guru.gallery.pojo.Album;
import com.interview.guru.gallery.view.MainActivityContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityModel implements MainActivityContract.Model {


    RetrofitHelper retrofitHelper;
    List<Album> mAlbumDataList = new ArrayList<>();

    MainActivityContract.onNetworkOperation networkOperation;

    public MainActivityModel(MainActivityContract.onNetworkOperation onNetworkOperation) {

        this.networkOperation = onNetworkOperation;
    }


    public  List<Album> mGetAlbumData() {

        retrofitHelper = new RetrofitHelper(Constants.BASE_URL);

        RequestsClient requestsClient = retrofitHelper.getRetrofit().create(RequestsClient.class);

        Call<List<Album>> call = requestsClient.getAlbumData();

        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {

                mAlbumDataList.clear();
                mAlbumDataList.addAll(response.body());
                Log.d("size", String.valueOf(response.body().size()));
                networkOperation.onResponseComplete(mAlbumDataList);


            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

                Log.d("MainActivity", "network call Failed");

            }
        });


        return mAlbumDataList;


    }
}
