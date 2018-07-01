package com.bway.my_demo_shopping.mvp.classes.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bway.my_demo_shopping.R;
import com.bway.my_demo_shopping.app.MyApp;
import com.bway.my_demo_shopping.mvp.classes.model.bean.RightBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MySmallerAdapter extends RecyclerView.Adapter{

    private List<RightBean.DataBean> list;

    public MySmallerAdapter(List<RightBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(parent.getContext(), R.layout.item2, null);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((MyHolder)holder).textView.setText(list.get(position).getList().get(position).getName());
        String pic=list.get(position).getList().get(position).getIcon();
        ImageLoader.getInstance().displayImage(pic,((MyHolder)holder).imageView, MyApp.getOptions());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item2_img);
            textView = itemView.findViewById(R.id.item2_tv);
        }
    }
}
