package com.interview.guru.gallery.pojo;

import com.google.gson.annotations.SerializedName;

public class AlbumData {

    @SerializedName("albumId")
    int albumId;

    @SerializedName("id")
    int id;

    @SerializedName("title")
    String  title;

    @SerializedName("url")
    String url;

    @SerializedName("thumbnailUrl")
    String thumbnailUrl;


    public int getAlbumId() {
        return albumId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
