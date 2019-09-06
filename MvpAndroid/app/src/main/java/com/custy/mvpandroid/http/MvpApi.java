package com.custy.mvpandroid.http;

import com.custy.mvpandroid.bean.BannerBean;
import com.custy.mvpandroid.bean.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MvpApi {
    @GET("banner/json")
    Call<Response<List<BannerBean>>> getBannerList();
}
