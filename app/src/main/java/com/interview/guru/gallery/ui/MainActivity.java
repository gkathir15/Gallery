package com.interview.guru.gallery.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.interview.guru.gallery.interfaces.OnItemClickListener;
import com.interview.guru.gallery.R;
import com.interview.guru.gallery.adapter.AlbumRecyclerAdapter;
import com.interview.guru.gallery.model.MainActivityModel;
import com.interview.guru.gallery.pojo.Album;
import com.interview.guru.gallery.presenter.MainActivityPresenter;
import com.interview.guru.gallery.view.MainActivityContract;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener,MainActivityContract.View ,MainActivityContract.onNetworkOperation{

    RecyclerView mAlbumListRecycler;
    AlbumRecyclerAdapter mAlbumRecyclerAdapter;
    List<Album> mAlbumDataList = new ArrayList<>();
    MainActivityContract.Presenter presenter;
    MainActivityModel mainActivityModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAlbumListRecycler = findViewById(R.id.albumDataRecyclerView);
        mAlbumListRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAlbumRecyclerAdapter = new AlbumRecyclerAdapter(R.layout.album_data,mAlbumDataList);
        mAlbumListRecycler.setAdapter(mAlbumRecyclerAdapter);
        mAlbumRecyclerAdapter.setClickListener(this);

        mainActivityModel = new MainActivityModel(this);
        presenter = new MainActivityPresenter(this,mainActivityModel);





//        mGetAlbumData();

    }

//    RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(getApplicationContext()){
//
//
//
//    };

//    void mGetAlbumData()
//    {
//        retrofitHelper = new RetrofitHelper(Constants.BASE_URL);
//
//        RequestsClient requestsClient = retrofitHelper.getRetrofit().create(RequestsClient.class);
//
//        Call<List<Album>> call  = requestsClient.getAlbumData();
//
//        call.enqueue(new Callback<List<Album>>() {
//            @Override
//            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
//
//                mAlbumDataList.clear();
//                mAlbumDataList.addAll(response.body());
//                Log.d("size", String.valueOf(response.body().size()));
//                mAlbumRecyclerAdapter.notifyDataSetChanged();
//                mAlbumListRecycler.getLayoutManager().scrollToPosition(mAlbumDataList.size()/2);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Album>> call, Throwable t) {
//
//                Log.d("MainActivity","network call Failed");
//
//            }
//        });




   // }


    @Override
    public void onClick(View View, int pPosition) {

        Log.d("clicked position",String.valueOf(pPosition));
        Intent lIntent = new Intent(MainActivity.this,AlbumDetailActivity.class);
        lIntent.putExtra("SelectedID",mAlbumDataList.get(pPosition).getId());
        startActivity(lIntent);

      //  mAlbumListRecycler.smoothScrollToPosition(mAlbumDataList.size());
        //mAlbumListRecycler.scrollToPosition(mAlbumDataList.size());
    }

    @Override
    public void initView(List<Album> albums) {

        mAlbumDataList.addAll(albums);
            Log.d("size main", String.valueOf(albums));




    }

    @Override
    public void onResponseComplete(List<Album> mAlbumDataList) {

        this.mAlbumDataList.addAll(mAlbumDataList);



        mAlbumRecyclerAdapter.notifyDataSetChanged();

    }
}
