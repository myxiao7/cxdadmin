package com.zh.cxdadmin.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.zh.cxdadmin.R;
import com.zh.cxdadmin.adapter.OrderServiceAdapter;
import com.zh.cxdadmin.base.BaseFragment;

import com.zh.cxdadmin.ui.order.OrderServiceFragment;
import com.zh.cxdadmin.ui.order.OrderWaitFragment;
import com.zh.cxdadmin.ui.order.OrderfinishFragment;
import com.zh.cxdadmin.ui.order.testFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/11/22.
 */

public class OrderSwitchFragment extends BaseFragment {
    @Bind(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    public static OrderSwitchFragment newInstance() {
        OrderSwitchFragment orderSwitchFragment = new OrderSwitchFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("", 0);
        orderSwitchFragment.setArguments(bundle);
        return orderSwitchFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_switch, container, false);
        ButterKnife.bind(this, view);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getChildFragmentManager(),
                FragmentPagerItems.with(activity)
                        .add(R.string.order_item01, OrderWaitFragment.class)
                        .add(R.string.order_item02, OrderServiceFragment.class)
                        .add(R.string.order_item03, OrderfinishFragment.class)
                        .create());
        viewpager.setOffscreenPageLimit(2);
        viewpager.setAdapter(adapter);
        viewpagertab.setViewPager(viewpager);
        return view;
    }

    /*class MyFragmentPagerItemAdapter extends FragmentPagerItemAdapter{

        public MyFragmentPagerItemAdapter(FragmentManager fm, FragmentPagerItems pages) {
            super(fm, pages);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }*/
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
