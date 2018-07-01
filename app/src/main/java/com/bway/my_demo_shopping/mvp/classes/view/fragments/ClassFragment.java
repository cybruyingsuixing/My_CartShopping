package com.bway.my_demo_shopping.mvp.classes.view.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bway.my_demo_shopping.R;
import com.bway.my_demo_shopping.base.BaseFragment;
import com.bway.my_demo_shopping.mvp.classes.model.bean.LeftBean;
import com.bway.my_demo_shopping.mvp.classes.model.bean.RightBean;
import com.bway.my_demo_shopping.mvp.classes.presenter.ClassesPresenter;
import com.bway.my_demo_shopping.mvp.classes.view.adapter.MyBigAdapter;
import com.bway.my_demo_shopping.mvp.classes.view.adapter.MyListViewAdapter;
import com.bway.my_demo_shopping.mvp.classes.view.view.ClassView;

import java.util.List;

public class ClassFragment extends BaseFragment<ClassesPresenter> implements ClassView {

    private static final String TAG = "ClassFragment*****";
    private ListView listView;
    private RecyclerView recyclerView;
    private MyListViewAdapter myListViewAdapter;


    @Override
    protected ClassesPresenter provide() {
        return presenter = new ClassesPresenter(this);
    }

    @Override
    protected int provId() {
        return R.layout.calssesfragment;
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void listener() {

    }

    @Override
    protected void initView(View view) {
        //获取id
        listView = view.findViewById(R.id.class_listview);
        recyclerView = view.findViewById(R.id.class_recyler);

        presenter.getLeft();
    }


    @Override
    public void onLeftSuccess(final LeftBean leftBean) {

        Toast.makeText(getContext(), "wwww", Toast.LENGTH_SHORT).show();
        List<LeftBean.DataBean> data = leftBean.getData();
        Log.d(TAG, "onLeftSuccess: " + data.size());

        myListViewAdapter = new MyListViewAdapter(data);
        listView.setAdapter(myListViewAdapter);
        presenter.getRight(1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                List<LeftBean.DataBean> data1 = leftBean.getData();
                int cid = data1.get(position).getCid();
                Log.d(TAG, "onItemClick: "+cid);
                Toast.makeText(getContext(),"dddd"+cid,Toast.LENGTH_SHORT).show();
                presenter.getRight(cid);
            }
        });
    }


    @Override
    public void onLeftFaild(String error) {
        Log.d(TAG, "onLeftFaild: " + error);
    }

    @Override
    public void onRightSuccess(RightBean rightBean) {
        List<RightBean.DataBean> data = rightBean.getData();
        Log.d(TAG, "----------------: "+data.get(0).getName());
        Log.d(TAG, "----------------: "+data.get(1).getName());
        MyBigAdapter myBigAdapter = new MyBigAdapter(data, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(myBigAdapter);
    }

    @Override
    public void onRightFaild(String error) {

    }
}
