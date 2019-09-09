package com.custy.mvpandroid.module.home;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.custy.mvpandroid.BaseFragment;
import com.custy.mvpandroid.Constant;
import com.custy.mvpandroid.InitApp;
import com.custy.mvpandroid.R;
import com.custy.mvpandroid.bean.BannerBean;
import com.custy.mvpandroid.bean.Response;
import com.custy.mvpandroid.http.RetrofitManager;
import com.custy.mvpandroid.http.ServerApi;
import com.custy.mvpandroid.widget.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends BaseFragment implements HomeContract.View {
    private HomeContract.Presenter mPresenter;
    private HomePresenter mHomePresenter;
    private Banner mBanner;
    private RecyclerView mRecyclerView;
    private static HomeFragment instance;
    private HomeListAdapter mAdapter;

    public static HomeFragment getInstance() {
        if (instance == null) {
            instance = new HomeFragment();
        }
        return instance;
    }




    @Override
    protected void initView(View root) {
        mRecyclerView = root.findViewById(R.id.home_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(InitApp.mAppContext, RecyclerView.VERTICAL, false));
        mAdapter = new HomeListAdapter(R.layout.item_home_list, null);
        mRecyclerView.setAdapter(mAdapter);
        mBanner = root.findViewById(R.id.home_banner);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.setBannerAnimation(Transformer.CubeIn);
        mBanner.startAutoPlay();
        mBanner.setDelayTime(5000);
        mBanner.setImageLoader(new GlideImageLoader());
        mHomePresenter=new HomePresenter(this);
    }
    @Override
    protected void loadData() {
        mHomePresenter.loadBinner();
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showNetError() {

    }

    @Override
    public void refereshBinner(List<BannerBean> data) {
        List<String> urls = new ArrayList<>(data.size());
        List<String> titles = new ArrayList<>(data.size());
        for (BannerBean bean : data) {
            urls.add(bean.getImagePath());
            titles.add(bean.getTitle());
        }
        mBanner.setImages(urls);
        mBanner.setBannerTitles(titles);
        mBanner.start();
    }
}
