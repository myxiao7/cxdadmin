package com.zh.cxdadmin.ui.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zh.cxdadmin.R;
import com.zh.cxdadmin.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by win7 on 2016/11/21.
 */

public class testFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test, container, false);
        return view;
    }
}
