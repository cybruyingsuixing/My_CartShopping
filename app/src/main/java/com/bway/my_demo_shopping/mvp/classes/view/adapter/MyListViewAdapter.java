package com.bway.my_demo_shopping.mvp.classes.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bway.my_demo_shopping.R;
import com.bway.my_demo_shopping.mvp.classes.model.bean.LeftBean;

import java.util.List;

public class MyListViewAdapter extends BaseAdapter {
    List<LeftBean.DataBean> data;

    public MyListViewAdapter(List<LeftBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.list_tv.setText(data.get(position).getName());
        return convertView;
    }


    public static class ViewHolder {
        public View rootView;
        public TextView list_tv;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.list_tv = (TextView) rootView.findViewById(R.id.list_tv);
        }

    }
}
