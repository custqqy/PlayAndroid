package com.custy.mvpandroid.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.custy.mvpandroid.InitApp;

/**
 * 描述：判断网络的辅助类
 *
 */
public class NetUtils {

    /**
     * 判断是否有网络
     */
    public static boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) InitApp.mAppContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }
}
