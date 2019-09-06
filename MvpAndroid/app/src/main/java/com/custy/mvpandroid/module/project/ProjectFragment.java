package com.custy.mvpandroid.module.project;

import android.view.View;

import com.custy.mvpandroid.BaseFragment;
import com.custy.mvpandroid.R;

public class ProjectFragment extends BaseFragment {
    private static ProjectFragment instance;
    public static ProjectFragment getInstance(){
        if (instance==null) {
            instance=new ProjectFragment();
        }
        return instance;
    }

    @Override
    protected void initView(View root) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }
}
