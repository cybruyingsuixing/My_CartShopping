package com.bway.my_demo_shopping.mvp.cart.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.bway.my_demo_shopping.R;
import com.bway.my_demo_shopping.base.BaseFragment;
import com.bway.my_demo_shopping.mvp.cart.model.bean.CartBean;
import com.bway.my_demo_shopping.mvp.cart.presenter.CartPresenter;
import com.bway.my_demo_shopping.mvp.cart.view.adapter.ExpandViewAdapter;
import com.bway.my_demo_shopping.mvp.cart.view.view.CartView;

import java.util.List;

public class CartFragment extends BaseFragment<CartPresenter> implements CartView, View.OnClickListener {


    private CheckBox quanxuan;
    private ExpandableListView expandableListView;
    private Button btn_buy;
    private TextView he;
    private ExpandViewAdapter adapter;

    @Override
    protected CartPresenter provide() {
        return new CartPresenter((CartView) this);
    }

    @Override
    protected int provId() {
        return R.layout.cartfragment;
    }

    @Override
    protected void initData() {

        presenter.doCart();
    }

    @Override
    protected void listener() {

    }

    @Override
    protected void initView(View view) {

        //获取id
        quanxuan = view.findViewById(R.id.quanxuan);
        expandableListView = view.findViewById(R.id.cart_expand);
        btn_buy = view.findViewById(R.id.btn_buy);
        he = view.findViewById(R.id.he);
        quanxuan.setOnClickListener(this);

        //声明AMapLocationClient类对象
        AMapLocationClient mLocationClient = null;
//初始化定位
        mLocationClient = new AMapLocationClient(getContext());
//启动定位
        mLocationClient.startLocation();
//异步获取定位结果
        AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //解析定位结果
                        String city = amapLocation.getCity();
                        Toast.makeText(getContext(), "地址：" + city, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        //设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener);

    }

    @Override
    public void onSuccess(CartBean cartBean) {
        List<CartBean.DataBean> data = cartBean.getData();
        //创建适配器
        adapter = new ExpandViewAdapter(data);
        expandableListView.setAdapter(adapter);
        reFreshSelectedAndToTalPriceAndTotalAllNumber();
        adapter.setOnCartListChangeListener(new ExpandViewAdapter.onCartListChangeListener() {
            @Override
            public void SellerCheckChange(int groupPosition) {
                //设置商家
                boolean b = adapter.productStatus(groupPosition);
                //子类按钮跟着改变
                adapter.noProductStatus(groupPosition, !b);
                //刷新适配器
                adapter.notifyDataSetChanged();
                reFreshSelectedAndToTalPriceAndTotalAllNumber();
            }

            @Override
            public void onProductCheckedChange(int groupPosition, int childPosition) {

                adapter.changeCurrentProductStatus(groupPosition, childPosition);
                //刷新适配器
                adapter.notifyDataSetChanged();
                reFreshSelectedAndToTalPriceAndTotalAllNumber();
            }

            @Override
            public void onProductNumberChange(int groupPosition, int childPosition, int number) {
                //设置加减按钮
                adapter.changeCurrentNumberProduct(groupPosition, childPosition, number);
                //刷新适配器
                adapter.notifyDataSetChanged();
                reFreshSelectedAndToTalPriceAndTotalAllNumber();
            }
        });
        //展开二级列表
        for (int i = 0; i < data.size(); i++) {
            expandableListView.expandGroup(i);
        }
    }

    @Override
    public void onFaild(String error) {


    }


    public void reFreshSelectedAndToTalPriceAndTotalAllNumber() {
        //判断是否全部选中
        boolean allProductSelected = adapter.isAllProductSelected();
        quanxuan.setChecked(allProductSelected);
      /*  //刷新适配器
        adapter.notifyDataSetChanged();
        reFreshSelectedAndToTalPriceAndTotalAllNumber();*/
        //设置总价格
        float totalPrice = adapter.calcuteTotalPrice();
        he.setText("总价格(" + totalPrice + ")");
        //总数量
        int totalNum = adapter.cacuteTotalNum();
        btn_buy.setText("总数量" + totalNum);
    }


    @Override
    public void onClick(View v) {
        //底部全选按钮
        boolean allProductSelected = adapter.isAllProductSelected();
        adapter.changeAllProductStatus(!allProductSelected);
        //刷新适配器
        adapter.notifyDataSetChanged();
        reFreshSelectedAndToTalPriceAndTotalAllNumber();
    }
}
