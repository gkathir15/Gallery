package com.interview.guru.gallery.interfaces;

import com.interview.guru.gallery.pojo.Album;
import com.interview.guru.gallery.pojo.AlbumData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RequestsClient {


    @GET("albums")
    Call<List<Album>>getAlbumData();

    @GET("albums/{id}/photos")
    Call<List<AlbumData>>getAlbumDataByID(@Path("id") String id);
}
