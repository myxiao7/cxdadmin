package com.zh.cxdadmin.ui.order;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListAdapter;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;
import com.google.gson.reflect.TypeToken;
import com.zh.cxdadmin.R;
import com.zh.cxdadmin.adapter.CusAdapter;
import com.zh.cxdadmin.adapter.CusSellerAdapter;
import com.zh.cxdadmin.adapter.OrderWaitAdapter;
import com.zh.cxdadmin.adapter.SellerAdapter;
import com.zh.cxdadmin.base.BaseApplication;
import com.zh.cxdadmin.base.BaseFragment;
import com.zh.cxdadmin.config.HttpPath;
import com.zh.cxdadmin.entity.JsonModel;
import com.zh.cxdadmin.entity.OrderEntity;
import com.zh.cxdadmin.entity.OrdrListEntity;
import com.zh.cxdadmin.entity.ResultEntity;
import com.zh.cxdadmin.entity.SellerEntity;
import com.zh.cxdadmin.entity.UserInfoEntity;
import com.zh.cxdadmin.http.HttpUtil;
import com.zh.cxdadmin.http.RequestCallBack;
import com.zh.cxdadmin.utils.DbUtils;
import com.zh.cxdadmin.utils.DialogUtil;
import com.zh.cxdadmin.utils.DialogUtils;
import com.zh.cxdadmin.utils.GsonUtil;
import com.zh.cxdadmin.utils.ToastUtil;
import com.zh.cxdadmin.view.xlistview.XListView;

import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 待接单
 * Created by dell on 2016/11/22.
 */

public class OrderWaitFragment extends BaseFragment {
    private static final String TAG = "OrderWaitFragment";
    @Bind(R.id.listview)
    XListView listview;

    private List<OrderEntity> list = new ArrayList<>();
    private OrderWaitAdapter adapter;
    private UserInfoEntity entity;
    private int pageIndex = 1;
    private MaterialDialog dialog;

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
        init();
        adapter = new OrderWaitAdapter(activity, list);
        listview.setAdapter(adapter);
        getOrderList(true);
        return view;

    }

    private void init() {
        entity = DbUtils.getInstance().getPersonInfo();
        IntentFilter intentFilter = new IntentFilter(BaseApplication.ORDERWAIT);
        activity.registerReceiver(receiver,intentFilter);
        listview.setPullLoadEnable(false);
        listview.setPullRefreshEnable(true);
        listview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                listview.setEnabled(false);
                listview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getOrderList(true);
                    }
                },800);
            }

            @Override
            public void onLoadMore() {
                listview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getOrderList(false);
                    }
                },800);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getSellerList(list.get(i-1).getOrderid(), i-1);
            }
        });
    }

    /**
     * 获取待接单订单列表
     * @param isRefresh
     */
    private void getOrderList(final boolean isRefresh){
        if(isRefresh){
            pageIndex = 1;
        }else{
            pageIndex++;
        }
        String path = HttpPath.getPath(HttpPath.GETORDERLIST);
        RequestParams params = HttpUtil.params(path);
        params.addBodyParameter("uid", entity.getId()+"");
        params.addBodyParameter("tockens", entity.getTocken());
        params.addBodyParameter("type", "0");
        params.addBodyParameter("rows", "10");
        params.addBodyParameter("page", pageIndex+"");
        params.addBodyParameter("sidx", "");
        params.addBodyParameter("sord", "");
        HttpUtil.http().post(params, new RequestCallBack<String>(activity){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                LogUtil.d(result);
                Type type = new TypeToken<JsonModel<List<OrderEntity>>>(){}.getType();
                JsonModel<List<OrderEntity>> jsonModel = GsonUtil.GsonToBean(result, type);
                if(jsonModel.isSuccess()){
                    if(isRefresh){
                        list.clear();
                        if(jsonModel.hasData()){
                            list = jsonModel.getDataList();
                            adapter = new OrderWaitAdapter(activity, list);
                            listview.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }else{
//                            ToastUtil.showShort("没有数据");
                        }
                        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
                        String time = dateFormat.format(new Date());
                        listview.setRefreshTime(time);
                        if(list.size() >= 10){
                            listview.setPullLoadEnable(true);
                        }
                    }else{
                        if(jsonModel.hasData()){
                            list.addAll(jsonModel.getDataList());
                            adapter.notifyDataSetChanged();
                        }else{
                            listview.setPullLoadEnable(false);
                            ToastUtil.showShort("没有更多了");
                        }
                    }

                }else{
                    ToastUtil.showShort("获取失败");
                }
                listview.setEnabled(true);
                listview.stopRefresh();
                listview.stopLoadMore();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
//                ToastUtil.showShort(ex.getMessage());
            }
        });
    }

    /**
     * 获得符合条件的商家
     * @param orderId
     */
    private void getSellerList(final String orderId, final int position){

        String path = HttpPath.getPath(HttpPath.FINDSELLERLIST);
        RequestParams params = HttpUtil.params(path);
        params.addBodyParameter("uid", entity.getId()+"");
        params.addBodyParameter("tockens", entity.getTocken());
        params.addBodyParameter("orderid", orderId);
        HttpUtil.http().post(params, new RequestCallBack<String>(activity){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                LogUtil.d(result);
                Type type = new TypeToken<JsonModel<List<SellerEntity>>>(){}.getType();
                JsonModel<List<SellerEntity>> jsonModel = GsonUtil.GsonToBean(result, type);
                if(jsonModel.isSuccess()){
                    if(jsonModel.hasData()){
                        showSimpleList(orderId,jsonModel.getDataList(), position);
                    }else {
                        ToastUtil.showShort("没有符合条件的加盟商");
                    }
                }else{
                    ToastUtil.showShort("获取失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                LogUtil.d(ex.getMessage());
//                ToastUtil.showShort(ex.getMessage());
            }
        });
    }

    /**
     * 派单
     * @param orderID 订单号
     * @param sellerEntity 技师信息
     */
    private void giveOrder(String orderID,SellerEntity sellerEntity, final int position){
        DialogUtils.showProgress(activity, R.string.giveorder);
        String path = HttpPath.getPath(HttpPath.GIVEORDER);
        RequestParams params = HttpUtil.params(path);
        params.addBodyParameter("uid", entity.getId()+"");
        params.addBodyParameter("tockens", entity.getTocken());
        params.addBodyParameter("orderid", orderID);
        params.addBodyParameter("operid", sellerEntity.getId()+"");
        params.addBodyParameter("oper", sellerEntity.getName());
        params.addBodyParameter("operphone", sellerEntity.getMobile());
        HttpUtil.http().post(params, new RequestCallBack<String>(activity){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                DialogUtils.stopProgress(activity);
                LogUtil.d(result);
                Type type = new TypeToken<ResultEntity>(){}.getType();
                ResultEntity resultEntity = GsonUtil.GsonToBean(result, type);
                if(resultEntity.isSuccee()){
                    ToastUtil.showShort("派单成功");
                    //移除当前订单
                    adapter.removeItem(position);
                    adapter.notifyDataSetChanged();
                }else{
                    ToastUtil.showShort("派单失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                LogUtil.d(ex.getMessage());
                DialogUtils.stopProgress(activity);
//                ToastUtil.showShort(ex.getMessage());
            }
        });
    }

    /**
     * 显示符合的技师列表
     * @param orderID 订单ID
     * @param list 技师列表
     * @param position 第几项
     */
    public void showSimpleList(final String orderID, final List<SellerEntity> list, final int position) {
        dialog =  new MaterialDialog.Builder(activity)
                .title("派单")
                .adapter(new CusSellerAdapter(list), new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        giveOrder(orderID, list.get(which), position);
                        dialog.dismiss();
                    }
                })
                .show();
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(BaseApplication.ORDERWAIT)){
                LogUtil.d("刷新未接单列表000");
                getOrderList(true);
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        activity.unregisterReceiver(receiver);
    }
}
