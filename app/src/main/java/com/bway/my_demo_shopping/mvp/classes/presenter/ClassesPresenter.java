package com.bway.my_demo_shopping.mvp.classes.presenter;

import com.bway.my_demo_shopping.base.BasePresenter;
import com.bway.my_demo_shopping.mvp.classes.model.ClassesModel;
import com.bway.my_demo_shopping.mvp.classes.model.bean.LeftBean;
import com.bway.my_demo_shopping.mvp.classes.model.bean.RightBean;
import com.bway.my_demo_shopping.mvp.classes.view.view.ClassView;

public class ClassesPresenter extends BasePresenter<ClassView> {


    private ClassesModel classesModel;
    private static final String TAG = "ClassesPresenter*****";
    public ClassesPresenter(ClassView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        classesModel = new ClassesModel();
    }

    public void getLeft() {
        classesModel.doLeftNet(new ClassesModel.onLeftCallBack() {
            @Override
            public void onLeftSuccess(LeftBean leftBean) {
                if (view != null) {
                    view.onLeftSuccess(leftBean);
                }
            }

            @Override
            public void onLeftFaild(String error) {

                if (view != null) {
                    view.onLeftFaild(error);
                }
            }
        });
    }


    public void getRight(int cid) {
        classesModel.doRightNet(cid, new ClassesModel.onRightCallBack() {
            @Override
            public void onRightSuccess(RightBean rightBean) {
                if (view != null) {
                    view.onRightSuccess(rightBean);
                }
            }

            @Override
            public void onRightFaild(String error) {

                if (view != null) {
                    view.onRightFaild(error);
                }
            }
        });
    }


}
