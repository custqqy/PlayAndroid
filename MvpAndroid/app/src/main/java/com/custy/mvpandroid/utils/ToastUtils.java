package com.custy.mvpandroid.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    private static Toast mToast;
    public static void showToast(Context mContext, String text){
        showToast(mContext,text,Toast.LENGTH_SHORT);
    }
    public static void showToast(Context mContext, String text, int duration) {
        if (mToast != null) {
            mToast.setText(text);
        } else {
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}
