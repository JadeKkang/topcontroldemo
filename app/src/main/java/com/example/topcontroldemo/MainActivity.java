package com.example.topcontroldemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.topcontrol.TopControl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TopControl.ItemClick {
    private List<Fragment> mFragmentList = new ArrayList<>();
    private TopControl topControl;
    private ViewPager vpHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vpHome = this.findViewById(R.id.vp_home);
        topControl = this.findViewById(R.id.top_control);

        topControl.setItemClick(this);

        mFragmentList.add(new LeftFragment());
        mFragmentList.add(new CenterFragment());
        mFragmentList.add(new RightFragment());

        viewPagerSet();
    }

    /**
     * ViewPager设置
     */
    private void viewPagerSet() {
        vpHome.setCurrentItem(0);
        vpHome.setPageTransformer(true, new DepthPageTransformer());
        vpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        topControl.setChoice(0);
                        break;
                    case 1:
                        topControl.setChoice(1);
                        break;
                    case 2:
                        topControl.setChoice(3);//注意  当ViewPager是3个的时候 当显示3个时候choice 是0 1 3 ；当显示2个时候choice 是0  3 ；当显示4个时候choice 是0  1  2  3
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vpHome.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }
            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });
    }

    @Override
    public void itemClick(View view, int i) {
        switch (i) {
            case 0:
                vpHome.setCurrentItem(0);
                break;
            case 1:
                vpHome.setCurrentItem(1);
                break;
            case 3:
                vpHome.setCurrentItem(2);
                break;
        }
    }
}
