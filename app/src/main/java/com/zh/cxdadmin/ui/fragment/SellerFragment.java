package com.zh.cxdadmin.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.zh.cxdadmin.R;
import com.zh.cxdadmin.base.BaseFragment;
import com.zh.cxdadmin.ui.order.testFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 订单
 * Created by win7 on 2016/11/21.
 */

public class SellerFragment extends BaseFragment {

    @Bind(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    public static SellerFragment newInstance() {
        SellerFragment orderFragment = new SellerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("", 0);
        orderFragment.setArguments(bundle);
        return orderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seller_switch, container, false);
        ButterKnife.bind(this, view);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getChildFragmentManager(),
                FragmentPagerItems.with(activity)
                        .add("已通过", testFragment.class)
                        .add("待审核", testFragment.class)
                        .add("未审核", testFragment.class)
        .create());
        viewpager.setAdapter(adapter);
        viewpagertab.setViewPager(viewpager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
