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

import com.google.gson.reflect.TypeToken;
import com.zh.cxdadmin.R;
import com.zh.cxdadmin.base.BaseActivity;
import com.zh.cxdadmin.config.HttpPath;
import com.zh.cxdadmin.entity.JsonModel;
import com.zh.cxdadmin.entity.OrderDetailEntity;
import com.zh.cxdadmin.entity.OrderEntity;
import com.zh.cxdadmin.entity.UserInfoEntity;
import com.zh.cxdadmin.http.HttpUtil;
import com.zh.cxdadmin.http.RequestCallBack;
import com.zh.cxdadmin.utils.DbUtils;
import com.zh.cxdadmin.utils.GsonUtil;
import com.zh.cxdadmin.utils.ImageLoaderHelper;

import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 已完成订单详情
 * Created by win7 on 2016/11/27.
 */

public class FinishDetailActivity extends BaseActivity {

    @Bind(R.id.toolbar_tv)
    TextView toolbarTv;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.findetail_icon_img)
    ImageView findetailIconImg;
    @Bind(R.id.findetail_name_tv)
    TextView findetailNameTv;
    @Bind(R.id.findetail_phone_tv)
    TextView findetailPhoneTv;
    @Bind(R.id.findetail_date_tv)
    TextView findetailDateTv;
    @Bind(R.id.findetail_phone_img)
    ImageView findetailPhoneImg;
    @Bind(R.id.findetail_verify_tv)
    TextView findetailVerifyTv;
    @Bind(R.id.findetail_give_tv)
    TextView findetailGiveTv;
    @Bind(R.id.findetail_count_tv)
    TextView findetailCountTv;
    @Bind(R.id.findetail_money_tv)
    TextView findetailMoneyTv;
    @Bind(R.id.findetail_add_tv)
    TextView findetailAddTv;
    @Bind(R.id.findetail_ordernum_tv)
    TextView findetailOrdernumTv;
    @Bind(R.id.findetail_givetime_tv)
    TextView findetailGivetimeTv;
    @Bind(R.id.findetail_gettime_tv)
    TextView findetailGettimeTv;
    @Bind(R.id.findetail_finishtime_tv)
    TextView findetailFinishtimeTv;


    private UserInfoEntity userEntity;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishdetail);
        ButterKnife.bind(this);
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
        userEntity = DbUtils.getInstance().getPersonInfo();
        id = this.getIntent().getStringExtra("id");
        getDetail(id);
    }

    private void initData(final OrderDetailEntity entity) {
        //头像
        if (!TextUtils.isEmpty(entity.getAppOperatorDTO().getAvatar())) {
            ImageLoaderHelper.getInstance().loadCirPic(findetailIconImg, entity.getAppOperatorDTO().getAvatar());
        }
        //昵称 + 姓名
        findetailNameTv.setText(entity.getAppOperatorDTO().getName());
        if (!TextUtils.isEmpty(entity.getAppOperatorDTO().getMobile())) {
            //手机号码
            findetailPhoneTv.setText(entity.getAppOperatorDTO().getMobile());
            findetailPhoneImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("tel:" + entity.getAppOperatorDTO().getMobile());
                    startActivity(new Intent(Intent.ACTION_DIAL, uri));
                }
            });
        }

        if (!TextUtils.isEmpty(entity.getAppOperatorDTO().getJoinustime())) {
            //注册时间
            findetailDateTv.setText("完成时间:" + entity.getAppOperatorDTO().getJoinustime());
        }
        //审核状态
        switch (entity.getAppOperatorDTO().getIspass()) {
            case 0:
                findetailVerifyTv.setText("审核状态：未通过");
                break;
            case 1:
                findetailVerifyTv.setText("审核状态：已通过");
                break;
            case 2:
                findetailVerifyTv.setText("审核状态：待审核");
                break;
        }

            //派单状态
        findetailGiveTv.setText(entity.getAppOperatorDTO().getIsaceptorders() == 0 ? "派单状态：不派单" : "派单状态：正常");

        if (!TextUtils.isEmpty(entity.getAppOperatorDTO().getOrdernum())) {
            //完成数量
            findetailCountTv.setText("完成订单数量：" + entity.getAppOperatorDTO().getOrdernum());
        }
        if (!TextUtils.isEmpty(entity.getAppOperatorDTO().getOrderamount())) {
            //完成金额
            findetailMoneyTv.setText("完成订单金额：" + entity.getAppOperatorDTO().getOrderamount());
        }

        if (!TextUtils.isEmpty(entity.getAppOperatorDTO().getLocation())) {
            //地址
            findetailAddTv.setText("所在地：" + entity.getAppOperatorDTO().getLocation());
        }
        //订单编号
        if (!TextUtils.isEmpty(entity.getOrdersDTO().getOrderid())) {
            findetailOrdernumTv.setText("订单编号："+entity.getOrdersDTO().getOrderid());
        }
        //下单时间
        if (!TextUtils.isEmpty(entity.getOrdersDTO().getOrderdate())) {
            findetailGivetimeTv.setText("下单时间："+entity.getOrdersDTO().getOrderdate());
        }
        //接单时间
        if (!TextUtils.isEmpty(entity.getOrdersDTO().getAcceptdate())) {
            findetailGettimeTv.setText("接单时间："+entity.getOrdersDTO().getAcceptdate());
        }
        //完成时间
        if (!TextUtils.isEmpty(entity.getOrdersDTO().getFinishdate())) {
            findetailFinishtimeTv.setText("完成时间："+entity.getOrdersDTO().getFinishdate());
        }

    }

    private void getDetail(String orderId){
        String path = HttpPath.getPath(HttpPath.GETORDERDETAIL);
        RequestParams params = HttpUtil.params(path);
        params.addBodyParameter("uid", userEntity.getId()+"");
        params.addBodyParameter("tockens", userEntity.getTocken());
        params.addBodyParameter("orderid", orderId);

        HttpUtil.http().post(params, new RequestCallBack<String>(activity){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                LogUtil.d(result);
                Type type = new TypeToken<OrderDetailEntity>(){}.getType();
                OrderDetailEntity entity = GsonUtil.GsonToBean(result, type);
                initData(entity);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
//                ToastUtil.showShort(ex.getMessage());
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.findetail_phone_img)
    public void onClick() {
    }
}
