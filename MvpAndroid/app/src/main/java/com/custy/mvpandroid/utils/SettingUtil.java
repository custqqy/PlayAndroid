package com.custy.mvpandroid.utils;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

import com.custy.mvpandroid.InitApp;
import com.custy.mvpandroid.R;


public class SettingUtil {
    public static SettingUtil getInstance() {
        return SettingsUtilInstance.instance;
    }
    private static final class SettingsUtilInstance {
        private static final SettingUtil instance = new SettingUtil();
    }
    private SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(InitApp.mAppContext);
    /**
     * 设置主题颜色
     */
    public void setColor(int color) {
        setting.edit().putInt("color", color).apply();
    }
    /**
     * 获取主题颜色
     */
    public int getColor() {
        int defaultColor = InitApp.mAppContext.getResources().getColor(R.color.colorPrimary);
        int color = setting.getInt("color", defaultColor);
        if ((color != 0) && Color.alpha(color) != 255) {
            return defaultColor;
        }
        return color;
    }
    /**
     * 获取图标值
     */
    public int getCustomIconValue() {
        String s = setting.getString("custom_icon", "0");
        return Integer.parseInt(s);
    }
    /**
     * 获取是否开启导航栏上色
     */
    public boolean getNavBar() {
        return setting.getBoolean("nav_bar", false);
    }
    /**
     * 设置导航栏上色
     */
    public void setNavBar(boolean b){
        setting.edit().putBoolean("nav_bar",b).apply();
    }
}
