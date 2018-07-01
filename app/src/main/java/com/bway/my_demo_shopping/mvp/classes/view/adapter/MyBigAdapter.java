package com.bway.my_demo_shopping.mvp.classes.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bway.my_demo_shopping.R;
import com.bway.my_demo_shopping.app.MyApp;
import com.bway.my_demo_shopping.mvp.classes.model.bean.RightBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MyBigAdapter extends RecyclerView.Adapter {

    private  RecyclerView recyclerView;
    private List<RightBean.DataBean> list;
    private Context context;

    public MyBigAdapter(List<RightBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(parent.getContext(), R.layout.recyler_item01, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((MyHolder)holder).textView.setText(list.get(position).getName());
      /*  String pic=list.get(position).getList().get(position).getIcon();
        ImageLoader.getInstance().displayImage(pic,((MySmallerAdapter.MyHolder)holder).imageView, MyApp.getOptions());*/

        MySmallerAdapter mySmallerAdapter = new MySmallerAdapter(list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mySmallerAdapter);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{


        private final TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.base01_recyler);
            textView = itemView.findViewById(R.id.base01_tv);
        }
    }


}
