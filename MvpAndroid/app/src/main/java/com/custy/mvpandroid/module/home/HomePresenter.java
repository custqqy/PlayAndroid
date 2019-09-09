package com.custy.mvpandroid.module.home;

import com.custy.mvpandroid.bean.BannerBean;
import com.custy.mvpandroid.bean.Response;
import com.custy.mvpandroid.http.RetrofitManager;
import com.custy.mvpandroid.http.ServerApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;

    public HomePresenter(HomeContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void loadBinner() {
        RetrofitManager.getRetrofit().create(ServerApi.class).getBannerList2()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<List<BannerBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<List<BannerBean>> listResponse) {
                        List<BannerBean> data = listResponse.data;
                        mView.refereshBinner(data);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadArticelList() {

    }

}
