package com.custy.mvpandroid.module.home;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.custy.mvpandroid.BaseFragment;
import com.custy.mvpandroid.Constant;
import com.custy.mvpandroid.InitApp;
import com.custy.mvpandroid.R;
import com.custy.mvpandroid.bean.BannerBean;
import com.custy.mvpandroid.bean.Response;
import com.custy.mvpandroid.http.MvpApi;
import com.custy.mvpandroid.widget.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends BaseFragment {
    private Banner mBanner;
    private RecyclerView mRecyclerView;
    private static HomeFragment instance;
    private HomeListAdapter mAdapter;

    public static HomeFragment getInstance(){
        if (instance==null) {
            instance=new HomeFragment();
        }
        return instance;
    }


    @Override
    protected void initView(View root) {
        mBanner=root.findViewById(R.id.home_banner);
        mRecyclerView=root.findViewById(R.id.home_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(InitApp.mAppContext,RecyclerView.VERTICAL,false));
        mAdapter = new HomeListAdapter(R.layout.item_home_list, getData());
        mRecyclerView.setAdapter(mAdapter);
        mBanner.setImageLoader(new GlideImageLoader());
//        mBanner.setImages(getImageList());
//        mBanner.start();
    }

    private List<Integer> getImageList() {
        List<Integer>list=new ArrayList<>();

        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);
//        list.add("http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E7%BE%8E%E5%A5%B3&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=3473128871,1574804327&os=682204536,490884976&simid=0,0&pn=4&rn=1&di=16601200&ln=1690&fr=&fmq=1567685801515_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fpic27.nipic.com%2F20130314%2F11899688_192542628000_2.jpg&rpstart=0&rpnum=0&adpicid=0&force=undefined");
//        list.add("http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E7%BE%8E%E5%A5%B3&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=1487351610,315303232&os=234252567,222961974&simid=0,0&pn=6&rn=1&di=16589100&ln=1690&fr=&fmq=1567685801515_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fpic33.nipic.com%2F20130924%2F9822353_015119969000_2.jpg&rpstart=0&rpnum=0&adpicid=0&force=undefined");
//        list.add("http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E7%BE%8E%E5%A5%B3&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=4016607068,4021246806&os=619995046,3991112566&simid=4118974903,549476096&pn=100&rn=1&di=145200&ln=1690&fr=&fmq=1567685801515_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=3c&objurl=http%3A%2F%2Fn.sinaimg.cn%2Fsports%2F20161220%2FH4Uw-fxytqax6757480.jpg&rpstart=0&rpnum=0&adpicid=0&force=undefined");
//        list.add("http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E7%BE%8E%E5%A5%B3&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=4033919967,4068691540&os=488628610,927961997&simid=0,0&pn=152&rn=1&di=86900&ln=1690&fr=&fmq=1567685801515_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=78&objurl=http%3A%2F%2Fpic.eastlady.cn%2Fuploads%2Ftp%2F201708%2F9999%2F4fce88791e.jpg&rpstart=0&rpnum=0&adpicid=0&force=undefined");
        return list;
    }

    private List<String> getData() {
        List<String>list=new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("num"+i);
        }
        return list;
    }

    @Override
    public void onResume() {
        super.onResume();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MvpApi api = retrofit.create(MvpApi.class);
        Call<Response<List<BannerBean>>> call = api.getBannerList();
        call.enqueue(new Callback<Response<List<BannerBean>>>() {
            @Override
            public void onResponse(Call<Response<List<BannerBean>>> call, retrofit2.Response<Response<List<BannerBean>>> response) {
                List<BannerBean> data = response.body().data;
                List<String>list=new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    list.add(data.get(i).getImagePath());
                }
                mBanner.setImages(list);
                mBanner.start();
            }

            @Override
            public void onFailure(Call<Response<List<BannerBean>>> call, Throwable t) {

            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
}
