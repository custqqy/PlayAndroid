package com.custy.mvpandroid;

public interface BaseView<T> {
    void setPresenter(T presenter);
    void showToast(String msg);

    void showNetError();
}
