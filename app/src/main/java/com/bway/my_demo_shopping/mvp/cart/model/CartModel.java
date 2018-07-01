package com.bway.my_demo_shopping.mvp.cart.model;

import com.bway.my_demo_shopping.mvp.cart.model.bean.CartBean;
import com.bway.my_demo_shopping.utils.HttpConfig;
import com.bway.my_demo_shopping.utils.OkHttpUtils;
import com.google.gson.Gson;

public class CartModel {


    public void getCart(final onCallBack onCallBack) {
        String url = HttpConfig.URL_CART;
        OkHttpUtils.getInstance().doGet(url, new OkHttpUtils.onCallBack() {
            @Override
            public void onFaild(Exception e) {
                if (onCallBack != null) {
                    onCallBack.onFaild("错了");
                }
            }

            @Override
            public void onResponse(String json) {
                Gson gson = new Gson();
                CartBean cartBean = gson.fromJson(json, CartBean.class);
                String code = cartBean.getCode();
                if ("0".equalsIgnoreCase(code)) {
                    if (onCallBack != null) {
                        onCallBack.onSuccess(cartBean);
                    }
                }
            }
        });

    }

    public interface onCallBack {
        void onSuccess(CartBean cartBean);

        void onFaild(String error);
    }
}
