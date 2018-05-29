package com.interview.guru.gallery.presenter;

import com.interview.guru.gallery.model.AlbumActivityModel;
import com.interview.guru.gallery.model.MainActivityModel;
import com.interview.guru.gallery.view.AlbumActivityContract;
import com.interview.guru.gallery.view.MainActivityContract;

public class AlbumActivityPresenter implements AlbumActivityContract.Presenter {

    private AlbumActivityContract.View mContractView;
    private AlbumActivityContract.Model model;


    public AlbumActivityPresenter(AlbumActivityContract.View mView, AlbumActivityContract.Model model) {

        this.model = model;

        mContractView = mView;

        initPresenter();

    }

    private void initPresenter() {

        //model = new MainActivityModel(onNetworkOperation);
        mContractView.initView(model.mGetAlbumData());
    }

    @Override
    public void fetchurl() {


        mContractView.initView(model.mGetAlbumData());

    }
}
