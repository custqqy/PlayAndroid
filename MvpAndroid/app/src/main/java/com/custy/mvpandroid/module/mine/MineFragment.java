package com.custy.mvpandroid.module.mine;

import android.view.View;

import com.custy.mvpandroid.BaseFragment;
import com.custy.mvpandroid.R;

public class MineFragment extends BaseFragment {
    private static MineFragment instance;
    public static MineFragment getInstance(){
        if (instance==null) {
            instance=new MineFragment();
        }
        return instance;
    }

    @Override
    protected void initView(View root) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }
}
