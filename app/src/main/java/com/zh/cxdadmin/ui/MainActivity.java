package com.zh.cxdadmin.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zh.cxdadmin.R;
import com.zh.cxdadmin.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar_tv)
    TextView toolbarTv;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.main_viewpager)
    ViewPager mainViewpager;
    @Bind(R.id.main_tab01)
    RadioButton mainTab01;
    @Bind(R.id.main_tab02)
    RadioButton mainTab02;
    @Bind(R.id.main_tab03)
    RadioButton mainTab03;
    @Bind(R.id.main_radiogroup)
    RadioGroup mainRadiogroup;

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
