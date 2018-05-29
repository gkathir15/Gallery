package com.interview.guru.gallery.view;

import com.interview.guru.gallery.pojo.Album;

import java.util.List;

public class MainActivityContract {

    public interface View{

        void initView(List<Album> albums);
    }

    public interface Model{

        List<Album> mGetAlbumData();

    }

    public interface Presenter{

        void fetchurl();

    }
     public interface onNetworkOperation{

        void onResponseComplete(List<Album> mAlbumDataList);

     }
}
