package com.custy.mvpandroid.module.home;

import com.custy.mvpandroid.BasePresenter;
import com.custy.mvpandroid.BaseView;
import com.custy.mvpandroid.bean.BannerBean;

import java.util.List;

public interface HomeContract {
    interface  Presenter extends BasePresenter {
        void loadBinner();

        void loadArticelList();
    }
    interface View extends BaseView<Presenter>{
        void refereshBinner(List<BannerBean> data);
    }
}
