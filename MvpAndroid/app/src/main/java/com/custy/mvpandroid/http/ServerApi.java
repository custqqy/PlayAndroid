package com.custy.mvpandroid.http;

import com.custy.mvpandroid.bean.BannerBean;
import com.custy.mvpandroid.bean.HomeBean;
import com.custy.mvpandroid.bean.Response;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServerApi {
    @GET("banner/json")
    Call<Response<List<BannerBean>>> getBannerList();

    @GET("banner/json")
    Observable <Response<List<BannerBean>>>getBannerList2();

    @GET("article/list/{page}/json")
    Observable <Response<List<HomeBean>>> getArticelList(@Path("page") int page);
}
