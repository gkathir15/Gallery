package com.interview.guru.gallery.ui;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.interview.guru.gallery.Constants;
import com.interview.guru.gallery.helpers.RetrofitHelper;
import com.interview.guru.gallery.interfaces.OnItemClickListener;
import com.interview.guru.gallery.R;
import com.interview.guru.gallery.interfaces.RequestsClient;
import com.interview.guru.gallery.adapter.AlbumDataRecyclerAdapter;
import com.interview.guru.gallery.pojo.AlbumData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumDetailActivity extends AppCompatActivity  implements OnItemClickListener {


    RecyclerView mImagesRecyclerView;
    int mRecievedID;
    AlbumDataRecyclerAdapter mAlbumDataRecyclerAdapter;

    List<AlbumData> mAlbumDataList  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);

        mRecievedID = getIntent().getIntExtra("SelectedID",0);

        mImagesRecyclerView = findViewById(R.id.album_details_recycler);
        mImagesRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mAlbumDataRecyclerAdapter = new AlbumDataRecyclerAdapter(R.layout.albumdata,mAlbumDataList);
        mImagesRecyclerView.setAdapter(mAlbumDataRecyclerAdapter);
        mAlbumDataRecyclerAdapter.setClickListener(this);


        mGetAlbumData(String.valueOf(mRecievedID));




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                mAlbumDataRecyclerAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                mAlbumDataRecyclerAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    void mGetAlbumData(String pathID)
    {

        RetrofitHelper retrofitHelper = new RetrofitHelper(Constants.BASE_URL);

        RequestsClient requestsClient = retrofitHelper.getRetrofit().create(RequestsClient.class);

        Call<List<AlbumData>> call  = requestsClient.getAlbumDataByID(pathID);

        call.enqueue(new Callback<List<AlbumData>>() {
            @Override
            public void onResponse(Call<List<AlbumData>> call, Response<List<AlbumData>> response) {

                mAlbumDataList.clear();
                mAlbumDataList.addAll(response.body());
                Log.d("size", String.valueOf(response.body().size()));
                mAlbumDataRecyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<AlbumData>> call, Throwable t) {

                Log.d("MainActivity","network call Failed");

            }
        });


    }

    @Override
    public void onClick(View View, int Position) {



    }
}
