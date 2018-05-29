package com.interview.guru.gallery.presenter;

import com.interview.guru.gallery.model.MainActivityModel;
import com.interview.guru.gallery.view.MainActivityContract;

public class MainActivityPresenter implements MainActivityContract.Presenter {


    private MainActivityContract.View mContractView;
    private MainActivityContract.Model model;


    public MainActivityPresenter(MainActivityContract.View mView, MainActivityModel model) {

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
