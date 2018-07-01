package com.bway.my_demo_shopping.mvp.classes.model;

import android.util.Log;

import com.bway.my_demo_shopping.mvp.classes.model.bean.LeftBean;
import com.bway.my_demo_shopping.mvp.classes.model.bean.RightBean;
import com.bway.my_demo_shopping.utils.HttpConfig;
import com.bway.my_demo_shopping.utils.OkHttpUtils;
import com.google.gson.Gson;

public class ClassesModel {

    private static final String TAG = "ClassesModel*****";
    public void doLeftNet(final onLeftCallBack onLeftCallBack) {

        String url = HttpConfig.URL_CLASS_ONE;
        OkHttpUtils.getInstance().doGet(url, new OkHttpUtils.onCallBack() {
            @Override
            public void onFaild(Exception e) {
                if (onLeftCallBack != null) {
                    onLeftCallBack.onLeftFaild(e.getMessage());
                }
            }

            @Override
            public void onResponse(String json) {

                Gson gson = new Gson();
                Log.d(TAG, "onResponse: "+json);
                LeftBean leftBean = gson.fromJson(json, LeftBean.class);
                String code = leftBean.getCode();
                Log.d(TAG, "onResponse: "+code);
                if ("0".equalsIgnoreCase(code)) {
                    if (onLeftCallBack != null) {
                        onLeftCallBack.onLeftSuccess(leftBean);
                    } else {
                        if (onLeftCallBack != null) {
                            onLeftCallBack.onLeftFaild("有错误");
                        }
                    }
                }
            }
        });
    }


    public void doRightNet(int cid, final onRightCallBack onRightCallBack) {

        String url = HttpConfig.URL_CLASS_TWO+"?cid="+cid;

        OkHttpUtils.getInstance().doGet(url, new OkHttpUtils.onCallBack() {
            @Override
            public void onFaild(Exception e) {
                if (onRightCallBack != null) {
                    onRightCallBack.onRightFaild("有错误");
                }
            }

            @Override
            public void onResponse(String json) {

                Gson gson = new Gson();
                RightBean rightBean = gson.fromJson(json, RightBean.class);
                String code = rightBean.getCode();
                if ("0".equalsIgnoreCase(code)) {
                    if (onRightCallBack != null) {
                        onRightCallBack.onRightSuccess(rightBean);
                    }
                } else {
                    if (onRightCallBack != null) {
                        onRightCallBack.onRightFaild("有错误");
                    }
                }
            }
        });


    }


    //定义接口(左)
    public interface onLeftCallBack {
        void onLeftSuccess(LeftBean leftBean);

        void onLeftFaild(String error);
    }
    //定义接口（右）

    public interface onRightCallBack {
        void onRightSuccess(RightBean rightBean);

        void onRightFaild(String error);
    }


}
