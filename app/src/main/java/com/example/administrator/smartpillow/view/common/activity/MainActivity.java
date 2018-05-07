package com.example.administrator.smartpillow.view.common.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.administrator.smartpillow.R;
import com.example.administrator.smartpillow.code.base.view.activity.BaseActivity;
import com.example.administrator.smartpillow.utils.ui.DrawableUtils;
import com.example.administrator.smartpillow.utils.ui.ToastUtils;
import com.example.administrator.smartpillow.view.common.factory.FragmentFactory;
import com.example.administrator.smartpillow.widget.viewpager.CustomViewPager;

public class MainActivity extends BaseActivity {
    private static String broadFlag = "Buttom_BroadFlag";
    CustomViewPager baseMentViewPager;
    BottomNavigationBar bottomNavigationBar;

    private Fragment mCurrentFragment;
    private static final int fragmentCount = 4;
    @Override
    public int getContentView() {
        return R.layout.activtiy_main;
    }

    @Override
    public void initView() {
        baseMentViewPager = findViewById(R.id.BaseMentViewPager);
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        mToolbarTitle.setText(getResources().getText(R.string.app_name));
        mToolbarSubTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getToastShort("点击了mToolbarSubTitle");
            }
        });
        navigationBar();

    }
    private void initToolbar(int position) {
        mToolbarSubTitle.setVisibility(View.INVISIBLE);
        switch (position){
            case 0:
                mToolbarTitle.setText(getResources().getText(R.string.app_name));
                break;
            case 1:
                mToolbarTitle.setText(getResources().getText(R.string.chat));
                mToolbarSubTitle.setVisibility(View.VISIBLE);
                break;
            case 2:
                mToolbarTitle.setText(getResources().getText(R.string.community));
                break;
            case 3:
                mToolbarTitle.setText(getResources().getText(R.string.personal));
                break;
        }
    }

    private void navigationBar() {
        baseMentViewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager() ));
        baseMentViewPager.setOffscreenPageLimit(0);//设置ViewPager的缓存界面数,默认缓存为2
        baseMentViewPager.setScanScroll(true);
        baseMentViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // position :当前页面，及你点击滑动的页面；positionOffset:当前页面偏移的百分比；positionOffsetPixels:当前页面偏移的像素位置
            }

            @Override
            public void onPageSelected(int position) {
                //当前选中的页面的Position
                bottomNavigationBar.selectTab(position);
                initToolbar(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //state ==1的时表示正在滑动，arg0==2的时表示滑动完毕了，arg0==0的时表示什么都没做。
            }
        });

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(DrawableUtils.getStateSelectDrawable(R.mipmap.home_green, R.mipmap.home_grey),
                        "首页"))
                .addItem(new BottomNavigationItem(DrawableUtils.getStateSelectDrawable(R.mipmap.classify_tour_green, R.mipmap.classify_tour_grey),
                        "群聊"))
                .addItem(new BottomNavigationItem(DrawableUtils.getStateSelectDrawable(R.mipmap.order_tour_green, R.mipmap.order_tour_grey),
                        "社区"))
                .addItem(new BottomNavigationItem(DrawableUtils.getStateSelectDrawable(R.mipmap.my_green, R.mipmap.my_grey),
                        "我的"));
        bottomNavigationBar.initialise();
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int i) {
                Intent intent2 = new Intent(broadFlag);
                intent2.putExtra("position", i);
                sendBroadcast(intent2);
                switch (i) {
                    case 0:
                        baseMentViewPager.setCurrentItem(0);
                        break;
                    case 1:
                        baseMentViewPager.setCurrentItem(1);
                        break;
                    case 2:
                        baseMentViewPager.setCurrentItem(2);
                        break;
                    case 3:
                        baseMentViewPager.setCurrentItem(3);
                        break;
                }
            }

            @Override
            public void onTabUnselected(int i) {

            }

            @Override
            public void onTabReselected(int i) {

            }
        });
    }

    @Override
    public void parseIntent(Intent intent) {

    }
    private class TabPagerAdapter extends FragmentStatePagerAdapter {

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            mCurrentFragment = FragmentFactory.createFragment(position);
            return mCurrentFragment;
        }

        @Override
        public int getCount() {
            return fragmentCount;
        }

    }

    @Override
    public int getStatusBarResource() {
        return R.color.translucent;
    }

}
