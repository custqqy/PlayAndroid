package com.custy.mvpandroid;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.custy.mvpandroid.utils.SettingUtil;

public class InitApp extends MultiDexApplication {
    public static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
        //开启导航栏上色
        SettingUtil.getInstance().setNavBar(true);
    }
}
