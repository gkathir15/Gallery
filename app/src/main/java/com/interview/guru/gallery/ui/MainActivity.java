package com.interview.guru.gallery.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.interview.guru.gallery.Constants;
import com.interview.guru.gallery.interfaces.OnItemClickListener;
import com.interview.guru.gallery.R;
import com.interview.guru.gallery.interfaces.RequestsClient;
import com.interview.guru.gallery.adapter.AlbumRecyclerAdapter;
import com.interview.guru.gallery.pojo.Album;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    RecyclerView mAlbumListRecycler;
    AlbumRecyclerAdapter mAlbumRecyclerAdapter;
    List<Album> mAlbumDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAlbumListRecycler = findViewById(R.id.albumDataRecyclerView);
        mAlbumListRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAlbumRecyclerAdapter = new AlbumRecyclerAdapter(R.layout.album_data,mAlbumDataList);
        mAlbumListRecycler.setAdapter(mAlbumRecyclerAdapter);
        mAlbumRecyclerAdapter.setClickListener(this);


        mGetAlbumData();

    }

    void mGetAlbumData()
    {

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        ).build();

        RequestsClient requestsClient = retrofit.create(RequestsClient.class);

        Call<List<Album>> call  = requestsClient.getAlbumData();

        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {

                mAlbumDataList.clear();
                mAlbumDataList.addAll(response.body());
                Log.d("size", String.valueOf(response.body().size()));
                mAlbumRecyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

                Log.d("MainActivity","network call Failed");

            }
        });


    }


    @Override
    public void onClick(View View, int pPosition) {

        Log.d("clicked position",String.valueOf(pPosition));
        Intent lIntent = new Intent(MainActivity.this,AlbumDetailActivity.class);
        lIntent.putExtra("SelectedID",mAlbumDataList.get(pPosition).getId());
        startActivity(lIntent);
    }
}
