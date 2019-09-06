package com.custy.mvpandroid.module.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.custy.mvpandroid.R;

import java.util.List;

public class HomeListAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public HomeListAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.item_title,item);
    }
}
