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
import com.zh.cxdadmin.base.BaseTitleFragment;
import com.zh.cxdadmin.ui.seller.SellerNotPassFragment;
import com.zh.cxdadmin.ui.seller.SellerPassFragment;
import com.zh.cxdadmin.ui.seller.SellerWaitFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/11/22.
 */

public class SellerSwitchFragment extends BaseTitleFragment {

    @Bind(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    public static SellerSwitchFragment newInstance() {
        SellerSwitchFragment sellerSwitchFragment = new SellerSwitchFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("", 0);
        sellerSwitchFragment.setArguments(bundle);
        return sellerSwitchFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seller_switch, container, false);
        ButterKnife.bind(this, view);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getChildFragmentManager(),
                FragmentPagerItems.with(activity)
                        .add(R.string.seller_item01, SellerPassFragment.class)
                        .add(R.string.seller_item02, SellerWaitFragment.class)
                        .add(R.string.seller_item03, SellerNotPassFragment.class)
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
