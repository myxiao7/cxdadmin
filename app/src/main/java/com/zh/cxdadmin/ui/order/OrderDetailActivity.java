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
import com.zh.cxdadmin.entity.OrderDetailEntity;
import com.zh.cxdadmin.entity.UserInfoEntity;
import com.zh.cxdadmin.http.HttpUtil;
import com.zh.cxdadmin.http.RequestCallBack;
import com.zh.cxdadmin.utils.DbUtils;
import com.zh.cxdadmin.utils.GsonUtil;
import com.zh.cxdadmin.utils.ImageLoaderHelper;

import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

import java.lang.reflect.Type;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 已完成订单详情
 * Created by win7 on 2016/11/27.
 */

public class OrderDetailActivity extends BaseActivity {


    @Bind(R.id.toolbar_tv)
    TextView toolbarTv;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.detail_cus_icon_img)
    ImageView detailCusIconImg;
    @Bind(R.id.detail_cus_name_tv)
    TextView detailCusNameTv;
    @Bind(R.id.detail_cus_phone_tv)
    TextView detailCusPhoneTv;
    @Bind(R.id.detail_cus_orderdate_tv)
    TextView detailCusOrderdateTv;
    @Bind(R.id.detail_cus_phone_img)
    ImageView detailCusPhoneImg;
    @Bind(R.id.detail_cus_type_tv)
    TextView detailCusTypeTv;
    @Bind(R.id.detail_cus_add_tv)
    TextView detailCusAddTv;
    @Bind(R.id.detail_cus_time_tv)
    TextView detailCusTimeTv;
    @Bind(R.id.detail_cus_price_tv)
    TextView detailCusPriceTv;
    @Bind(R.id.detail_cus_cartype_tv)
    TextView detailCusCartypeTv;
    @Bind(R.id.detail_cus_color_tv)
    TextView detailCusColorTv;
    @Bind(R.id.detail_cus_num_tv)
    TextView detailCusNumTv;
    @Bind(R.id.detail_cus_remark_tv)
    TextView detailCusRemarkTv;
    @Bind(R.id.detail_sell_icon_img)
    ImageView detailSellIconImg;
    @Bind(R.id.detail_sell_name_tv)
    TextView detailSellNameTv;
    @Bind(R.id.detail_sell_phone_tv)
    TextView detailSellPhoneTv;
    @Bind(R.id.detail_sell_date_tv)
    TextView detailSellDateTv;
    @Bind(R.id.detail_sell_phone_img)
    ImageView detailSellPhoneImg;
    @Bind(R.id.detail_sell_verify_tv)
    TextView detailSellVerifyTv;
    @Bind(R.id.detail_sell_give_tv)
    TextView detailSellGiveTv;
    @Bind(R.id.detail_sell_count_tv)
    TextView detailSellCountTv;
    @Bind(R.id.detail_sell_money_tv)
    TextView detailSellMoneyTv;
    @Bind(R.id.detail_sell_add_tv)
    TextView detailSellAddTv;
    @Bind(R.id.detail_sell_ordernum_tv)
    TextView detailSellOrdernumTv;
    @Bind(R.id.detail_sell_givetime_tv)
    TextView detailSellGivetimeTv;
    @Bind(R.id.detail_sell_gettime_tv)
    TextView detailSellGettimeTv;
    @Bind(R.id.detail_sell_finishtime_tv)
    TextView detailSellFinishtimeTv;
    private UserInfoEntity userEntity;
    private String id;
    private boolean isFinish;//是否已完成

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail);
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
        isFinish = this.getIntent().getBooleanExtra("state", true);
        getDetail(id);
    }

    private void initData(final OrderDetailEntity entity) {
        //用户
        //头像
        if (!TextUtils.isEmpty(entity.getOrdersDTO().getAvartar())) {
            ImageLoaderHelper.getInstance().loadCirPic(detailCusIconImg, entity.getOrdersDTO().getAvartar());
        }
        //昵称 + 姓名
        detailCusNameTv.setText(entity.getOrdersDTO().getName());
        if (!TextUtils.isEmpty(entity.getOrdersDTO().getMobile())) {
            //手机号码
            detailCusPhoneTv.setText(entity.getOrdersDTO().getMobile());
            detailCusPhoneImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("tel:" + entity.getOrdersDTO().getMobile());
                    startActivity(new Intent(Intent.ACTION_DIAL, uri));
                }
            });
        }

        if (!TextUtils.isEmpty(entity.getOrdersDTO().getPaydate())) {
            //支付时间
            detailCusOrderdateTv.setText("支付时间:" + entity.getOrdersDTO().getPaydate());
        }
        if (!TextUtils.isEmpty(entity.getOrdersDTO().getServicetypename())) {
            //洗车类型
            detailCusTypeTv.setText(entity.getOrdersDTO().getServicetypename());
        }
        if (!TextUtils.isEmpty(entity.getOrdersDTO().getLocation())) {
            //地址
            detailCusAddTv.setText(entity.getOrdersDTO().getLocation());
        }
        if (!TextUtils.isEmpty(entity.getOrdersDTO().getAppointment())) {
            //预约时间
            detailCusTimeTv.setText(entity.getOrdersDTO().getAppointment());
        }
        if (!TextUtils.isEmpty(entity.getOrdersDTO().getCarbrank())) {
            //车型
            detailCusCartypeTv.setText(entity.getOrdersDTO().getCarbrank());
        }
        if (!TextUtils.isEmpty(entity.getOrdersDTO().getCarcolor())) {
            //颜色
            detailCusColorTv.setText(entity.getOrdersDTO().getCarcolor());
        }
        if (!TextUtils.isEmpty(entity.getOrdersDTO().getCarno())) {
            //车牌
            detailCusNumTv.setText(entity.getOrdersDTO().getCarno());
        }
        if (!TextUtils.isEmpty(entity.getOrdersDTO().getOrderamount() + "")) {
            //价格
            detailCusPriceTv.setText("￥" + entity.getOrdersDTO().getOrderamount() + "");
        }

        if (!TextUtils.isEmpty(entity.getOrdersDTO().getRemark())) {
            //备注
            detailCusRemarkTv.setText(entity.getOrdersDTO().getRemark());
        }
        //商家
        //头像
        if (!TextUtils.isEmpty(entity.getAppOperatorDTO().getAvatar())) {
            ImageLoaderHelper.getInstance().loadCirPic(detailSellIconImg, HttpPath.HOST + entity.getAppOperatorDTO().getAvatar());
        }
        //昵称 + 姓名
        detailSellNameTv.setText(entity.getAppOperatorDTO().getName());
        if (!TextUtils.isEmpty(entity.getAppOperatorDTO().getMobile())) {
            //手机号码
            detailSellPhoneTv.setText(entity.getAppOperatorDTO().getMobile());
            detailSellPhoneImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("tel:" + entity.getAppOperatorDTO().getMobile());
                    startActivity(new Intent(Intent.ACTION_DIAL, uri));
                }
            });
        }

        if (!TextUtils.isEmpty(entity.getAppOperatorDTO().getJoinustime())) {
            //注册时间
            detailSellDateTv.setText(entity.getAppOperatorDTO().getJoinustime());
        }
        //审核状态
        switch (entity.getAppOperatorDTO().getIspass()) {
            case 0:
                detailSellVerifyTv.setText("审核状态：未通过");
                break;
            case 1:
                detailSellVerifyTv.setText("审核状态：已通过");
                break;
            case 2:
                detailSellVerifyTv.setText("审核状态：待审核");
                break;
        }

        //派单状态
        detailSellGiveTv.setText(entity.getAppOperatorDTO().getIsaceptorders() == 0 ? "派单状态：不派单" : "派单状态：正常");

        if (!TextUtils.isEmpty(entity.getAppOperatorDTO().getOrdernum())) {
            //完成数量
            detailSellCountTv.setText("完成订单数量：" + entity.getAppOperatorDTO().getOrdernum());
        }
        if (!TextUtils.isEmpty(entity.getAppOperatorDTO().getOrderamount())) {
            //完成金额
            detailSellMoneyTv.setText("完成订单金额：" + entity.getAppOperatorDTO().getOrderamount());
        }

        if (!TextUtils.isEmpty(entity.getAppOperatorDTO().getLocation())) {
            //地址
            detailSellAddTv.setText("所在地：" + entity.getAppOperatorDTO().getLocation());
        }
        //订单编号
        if (!TextUtils.isEmpty(entity.getOrdersDTO().getOrderid())) {
            detailSellOrdernumTv.setText("订单编号：" + entity.getOrdersDTO().getOrderid());
        }
        //下单时间
        if (!TextUtils.isEmpty(entity.getOrdersDTO().getOrderdate())) {
            detailSellGivetimeTv.setText("下单时间：" + entity.getOrdersDTO().getOrderdate());
        }
        //接单时间
        if (!TextUtils.isEmpty(entity.getOrdersDTO().getAcceptdate())) {
            detailSellGettimeTv.setText("接单时间：" + entity.getOrdersDTO().getAcceptdate());
        }
        if(isFinish){
            //完成时间
            if (!TextUtils.isEmpty(entity.getOrdersDTO().getFinishdate())) {
                detailSellFinishtimeTv.setText("完成时间：" + entity.getOrdersDTO().getFinishdate());
            }
        }else{
            detailSellFinishtimeTv.setText("未完成");
        }
    }

    private void getDetail(String orderId) {
        String path = HttpPath.getPath(HttpPath.GETORDERDETAIL);
        RequestParams params = HttpUtil.params(path);
        params.addBodyParameter("uid", userEntity.getId() + "");
        params.addBodyParameter("tockens", userEntity.getTocken());
        params.addBodyParameter("orderid", orderId);

        HttpUtil.http().post(params, new RequestCallBack<String>(activity) {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                LogUtil.d(result);
                Type type = new TypeToken<OrderDetailEntity>() {
                }.getType();
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

}
