package com.zh.cxdadmin.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.reflect.TypeToken;
import com.zh.cxdadmin.R;
import com.zh.cxdadmin.adapter.OrderServiceAdapter;
import com.zh.cxdadmin.adapter.OrderWaitAdapter;
import com.zh.cxdadmin.base.BaseFragment;
import com.zh.cxdadmin.config.HttpPath;
import com.zh.cxdadmin.entity.JsonModel;
import com.zh.cxdadmin.entity.OrderEntity;
import com.zh.cxdadmin.entity.UserInfoEntity;
import com.zh.cxdadmin.http.HttpUtil;
import com.zh.cxdadmin.http.RequestCallBack;
import com.zh.cxdadmin.ui.LoginActivity;
import com.zh.cxdadmin.utils.DbUtils;
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
 * 待服务
 * Created by dell on 2016/11/22.
 */

public class OrderServiceFragment extends BaseFragment {
    private static final String TAG = "OrderWaitFragment";
    @Bind(R.id.listview)
    XListView listview;

    private List<OrderEntity> list = new ArrayList<>();
    private OrderServiceAdapter adapter;
    private UserInfoEntity entity;
    private int pageIndex = 1;

    public static OrderServiceFragment newInstance() {
        OrderServiceFragment orderWaitFragment = new OrderServiceFragment();
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
        adapter = new OrderServiceAdapter(activity, list, false);
        listview.setAdapter(adapter);
        getOrderList(true);
        return view;

    }

    private void init() {
        entity = DbUtils.getInstance().getPersonInfo();
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
                Intent intent = new Intent(activity, OrderDetailActivity.class);
                intent.putExtra("id", list.get(i-1).getOrderid());
                intent.putExtra("state", false);
                startActivity(intent);
            }
        });
    }

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
        params.addBodyParameter("type", "2");
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
                if(jsonModel.isSuccess(activity)){
                    if(isRefresh){
                        list.clear();
                        if(jsonModel.hasData()){
                            list = jsonModel.getDataList();
                            adapter = new OrderServiceAdapter(activity, list, false);
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
                    ToastUtil.showShort(jsonModel.getError_desc());
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
