package com.interview.guru.gallery.model;

import android.util.Log;

import com.interview.guru.gallery.Constants;
import com.interview.guru.gallery.helpers.RetrofitHelper;
import com.interview.guru.gallery.interfaces.RequestsClient;
import com.interview.guru.gallery.pojo.AlbumData;
import com.interview.guru.gallery.view.AlbumActivityContract;
import com.interview.guru.gallery.view.MainActivityContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumActivityModel implements AlbumActivityContract.Model {


    RetrofitHelper retrofitHelper;
    List<AlbumData> mAlbumDataList = new ArrayList<>();
    String ID;

    AlbumActivityContract.onNetworkOperation networkOperation;

    public AlbumActivityModel(AlbumActivityContract.onNetworkOperation onNetworkOperation,String id) {
        ID = id;

        this.networkOperation = onNetworkOperation;
    }


    public List<AlbumData> mGetAlbumData() {

        retrofitHelper = new RetrofitHelper(Constants.BASE_URL);

        RequestsClient requestsClient = retrofitHelper.getRetrofit().create(RequestsClient.class);

        Call<List<AlbumData>> call = requestsClient.getAlbumDataByID(ID);

        call.enqueue(new Callback<List<AlbumData>>() {
            @Override
            public void onResponse(Call<List<AlbumData>> call, Response<List<AlbumData>> response) {

                mAlbumDataList.clear();
                mAlbumDataList.addAll(response.body());
                Log.d("size", String.valueOf(response.body().size()));
                networkOperation.onResponseComplete(mAlbumDataList);


            }

            @Override
            public void onFailure(Call<List<AlbumData>> call, Throwable t) {

                Log.d("albumActivity", "network call Failed");

            }
        });


        return mAlbumDataList;


    }
}
