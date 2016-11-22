package com.zh.cxdadmin.ui.order;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zh.cxdadmin.R;
import com.zh.cxdadmin.base.BaseFragment;
import com.zh.cxdadmin.view.xlistview.XListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/11/22.
 */

public class OrderWaitFragment extends BaseFragment {

    @Bind(R.id.listview)
    XListView listview;

    public static OrderWaitFragment newInstance() {
        OrderWaitFragment orderWaitFragment = new OrderWaitFragment();
        Bundle bundle = new Bundle();
        orderWaitFragment.setArguments(bundle);
        return orderWaitFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);
        ButterKnife.bind(this, view);
        listview.setPullRefreshEnable(true);
        listview.setPullLoadEnable(true);
        listview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listview.stopRefresh();
                    }
                },1200);
            }

            @Override
            public void onLoadMore() {

            }
        });
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
