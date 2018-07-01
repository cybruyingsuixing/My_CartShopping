package com.bway.my_demo_shopping.mvp.cart.view.view;

import com.bway.my_demo_shopping.base.IView;
import com.bway.my_demo_shopping.mvp.cart.model.bean.CartBean;

public interface CartView extends IView {

    void onSuccess(CartBean cartBean);

    void onFaild(String error);

}
