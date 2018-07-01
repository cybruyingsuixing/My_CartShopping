package com.bway.my_demo_shopping.mvp.cart.presenter;

import com.bway.my_demo_shopping.base.BasePresenter;
import com.bway.my_demo_shopping.mvp.cart.model.CartModel;
import com.bway.my_demo_shopping.mvp.cart.model.bean.CartBean;
import com.bway.my_demo_shopping.mvp.cart.view.view.CartView;

public class CartPresenter extends BasePresenter<CartView> {


    private CartModel cartModel;

    public CartPresenter(CartView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        cartModel = new CartModel();
    }

    public void doCart() {
        cartModel.getCart(new CartModel.onCallBack() {
            @Override
            public void onSuccess(CartBean cartBean) {
                if (view != null) {
                    view.onSuccess(cartBean);
                }
            }

            @Override
            public void onFaild(String error) {
                if (view != null) {
                    view.onFaild(error);
                }
            }
        });
    }
}
