package com.bway.my_demo_shopping.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public  abstract class BaseFragment <P extends BasePresenter> extends Fragment{


    protected P presenter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(provId(),null);
        presenter=provide();
        return view;
    }

    protected abstract P provide();

    protected abstract int provId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
          initView(view);
          listener();
          initData();
    }

    protected abstract void initData();

    protected abstract void listener();

    protected abstract void initView(View view);
}
