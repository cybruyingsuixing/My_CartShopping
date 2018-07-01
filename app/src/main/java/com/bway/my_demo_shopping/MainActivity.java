package com.bway.my_demo_shopping;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.bway.my_demo_shopping.mvp.cart.view.fragments.CartFragment;
import com.bway.my_demo_shopping.mvp.classes.view.fragments.ClassFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Fragment> list = new ArrayList<>();
    private RadioGroup radio;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        radio = findViewById(R.id.main_radio);
        viewPager = findViewById(R.id.main_viewpager);

        list.add(new ClassFragment());
        list.add(new CartFragment());
        MyFragment myFragment = new MyFragment(getSupportFragmentManager(), list);
        viewPager.setAdapter(myFragment);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        radio.check(R.id.main_classes);
                        break;
                    case 1:
                        radio.check(R.id.main_cart);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_classes:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.main_cart:
                        viewPager.setCurrentItem(1);
                        break;
                }
            }
        });
    }
}
