package com.zh.cxdadmin.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zh.cxdadmin.R;
import com.zh.cxdadmin.base.BaseActivity;
import com.zh.cxdadmin.ui.fragment.CustomerFragment;
import com.zh.cxdadmin.ui.fragment.OrderSwitchFragment;
import com.zh.cxdadmin.ui.fragment.SellerSwitchFragment;
import com.zh.cxdadmin.view.ScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity{

    @Bind(R.id.toolbar_tv)
    TextView toolbarTv;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.main_viewpager)
    ScrollViewPager mainViewpager;
    @Bind(R.id.main_tab01)
    RadioButton mainTab01;
    @Bind(R.id.main_tab02)
    RadioButton mainTab02;
    @Bind(R.id.main_tab03)
    RadioButton mainTab03;
    @Bind(R.id.main_radiogroup)
    RadioGroup mainRadiogroup;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbarTv.setText("订单");
        Fragment fragment01 = OrderSwitchFragment.newInstance();
        Fragment fragment02 = SellerSwitchFragment.newInstance();
        Fragment fragment03 = CustomerFragment.newInstance();
        fragments.add(fragment01);
        fragments.add(fragment02);
        fragments.add(fragment03);
        TabFragmentAdapter adapter = new TabFragmentAdapter(getSupportFragmentManager(), fragments);
        mainViewpager.setScrollEnable(false);
        mainViewpager.setAdapter(adapter);

        mainViewpager.setCurrentItem(0);
        mainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton radioButton = (RadioButton) mainRadiogroup.getChildAt(position);
                radioButton.setChecked(true);
                toolbarTv.setText(radioButton.getText().toString());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mainRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int indexOfChild = group.indexOfChild(group.findViewById(checkedId));
                mainViewpager.setCurrentItem(indexOfChild,true);
            }
        });
    }

    class TabFragmentAdapter extends FragmentPagerAdapter{
        private List<Fragment> list;

        public TabFragmentAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list=list;
        }


        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
