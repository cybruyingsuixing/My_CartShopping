package com.bway.my_demo_shopping.mvp.classes.view.view;

import com.bway.my_demo_shopping.base.IView;
import com.bway.my_demo_shopping.mvp.classes.model.bean.LeftBean;
import com.bway.my_demo_shopping.mvp.classes.model.bean.RightBean;

public interface ClassView extends IView {

    void onLeftSuccess(LeftBean leftBean);

    void onLeftFaild(String error);

    void onRightSuccess(RightBean rightBean);

    void onRightFaild(String error);


}
