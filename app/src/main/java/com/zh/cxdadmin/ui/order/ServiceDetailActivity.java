package com.zh.cxdadmin.ui.order;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zh.cxdadmin.R;
import com.zh.cxdadmin.base.BaseActivity;
import com.zh.cxdadmin.entity.OrderEntity;
import com.zh.cxdadmin.utils.ImageLoaderHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 待服务订单详情
 * Created by win7 on 2016/11/27.
 */

public class ServiceDetailActivity extends BaseActivity {
    @Bind(R.id.toolbar_tv)
    TextView toolbarTv;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.serdetail_icon_img)
    ImageView serdetailIconImg;
    @Bind(R.id.serdetail_name_tv)
    TextView serdetailNameTv;
    @Bind(R.id.serdetail_phone_tv)
    TextView serdetailPhoneTv;
    @Bind(R.id.serdetail_orderdate_tv)
    TextView serdetailOrderdateTv;
    @Bind(R.id.serdetail_phone_img)
    ImageView serdetailPhoneImg;
    @Bind(R.id.serdetail_type_tv)
    TextView serdetailTypeTv;
    @Bind(R.id.serdetail_add_tv)
    TextView serdetailAddTv;
    @Bind(R.id.serdetail_time_tv)
    TextView serdetailTimeTv;
    @Bind(R.id.serdetail_price_tv)
    TextView serdetailPriceTv;
    @Bind(R.id.serdetail_cartype_tv)
    TextView serdetailCartypeTv;
    @Bind(R.id.serdetail_color_tv)
    TextView serdetailColorTv;
    @Bind(R.id.serdetail_num_tv)
    TextView serdetailNumTv;
    @Bind(R.id.serdetail_remark_tv)
    TextView serdetailRemarkTv;

    private OrderEntity entity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicedetail);
        ButterKnife.bind(this);
        entity = this.getIntent().getParcelableExtra("detail");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbarTv.setText("订单详情");
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
        
        initData();
    }

    private void initData() {
        //头像
        if (!TextUtils.isEmpty(entity.getAvartar())) {
            ImageLoaderHelper.getInstance().loadCirPic(serdetailIconImg, entity.getAvartar());
        }
        //昵称 + 姓名
        serdetailNameTv.setText(entity.getName());
        if (!TextUtils.isEmpty(entity.getMobile())) {
            //手机号码
            serdetailPhoneTv.setText(entity.getMobile());
            serdetailPhoneImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("tel:" + entity.getMobile());
                    startActivity(new Intent(Intent.ACTION_DIAL, uri));
                }
            });
        }

        if (!TextUtils.isEmpty(entity.getOrderid())) {
            //接单时间
            serdetailOrderdateTv.setText("接单时间:" + entity.getAcceptdate());
        }
        if (!TextUtils.isEmpty(entity.getServicetypename())) {
            //洗车类型
            serdetailTypeTv.setText(entity.getServicetypename());
        }
        if (!TextUtils.isEmpty(entity.getLocation())) {
            //地址
            serdetailAddTv.setText(entity.getLocation());
        }
        if (!TextUtils.isEmpty(entity.getAppointment())) {
            //预约时间
            serdetailTimeTv.setText(entity.getAppointment());
        }
        if (!TextUtils.isEmpty(entity.getCarbrank())) {
            //车型
            serdetailCartypeTv.setText(entity.getCarbrank());
        }
        if (!TextUtils.isEmpty(entity.getCarcolor())) {
            //颜色
            serdetailColorTv.setText(entity.getCarcolor());
        }
        if (!TextUtils.isEmpty(entity.getCarno())) {
            //车牌
            serdetailNumTv.setText(entity.getCarno());
        }
        if (!TextUtils.isEmpty(entity.getOrderamount() + "")) {
            //价格
            serdetailPriceTv.setText("￥" + entity.getOrderamount() + "");
        }

        if (!TextUtils.isEmpty(entity.getRemark())) {
            //备注
            serdetailRemarkTv.setText(entity.getRemark());
        }
    }

    @OnClick(R.id.serdetail_phone_img)
    public void onClick() {
        if (!TextUtils.isEmpty(entity.getMobile())) {
            serdetailPhoneImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("tel:" + entity.getMobile());
                    startActivity(new Intent(Intent.ACTION_DIAL, uri));
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
