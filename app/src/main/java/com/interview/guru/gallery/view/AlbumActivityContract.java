package com.interview.guru.gallery.view;

import com.interview.guru.gallery.pojo.Album;
import com.interview.guru.gallery.pojo.AlbumData;

import java.util.List;

public class AlbumActivityContract {

    public interface View{

        void initView(List<AlbumData> albums);
    }

    public interface Model{

        List<AlbumData> mGetAlbumData();

    }

    public interface Presenter{

        void fetchurl();

    }
    public interface onNetworkOperation{

        void onResponseComplete(List<AlbumData> mAlbumDataList);

    }
}
