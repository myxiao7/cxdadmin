package com.zh.cxdadmin.ui.seller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.google.gson.reflect.TypeToken;
import com.zh.cxdadmin.R;
import com.zh.cxdadmin.adapter.SellerAdapter;
import com.zh.cxdadmin.base.BaseApplication;
import com.zh.cxdadmin.base.BaseFragment;
import com.zh.cxdadmin.config.HttpPath;
import com.zh.cxdadmin.entity.JsonModel;
import com.zh.cxdadmin.entity.ResultEntity;
import com.zh.cxdadmin.entity.SellerEntity;
import com.zh.cxdadmin.entity.UserInfoEntity;
import com.zh.cxdadmin.http.HttpUtil;
import com.zh.cxdadmin.http.RequestCallBack;
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
 * 未通过
 * Created by dell on 2016/11/22.
 */

public class SellerNotPassFragment extends BaseFragment {
    private static final String TAG = "OrderWaitFragment";
    @Bind(R.id.listview)
    XListView listview;

    private List<SellerEntity> list = new ArrayList<>();
    private SellerAdapter adapter;
    private UserInfoEntity entity;
    private int pageIndex = 1;

    public static SellerNotPassFragment newInstance() {
        SellerNotPassFragment orderWaitFragment = new SellerNotPassFragment();
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
        adapter = new SellerAdapter(activity, list);
        listview.setAdapter(adapter);
        getSellerList(true);
        return view;

    }

    private void init() {
        entity = DbUtils.getInstance().getPersonInfo();
        IntentFilter intentFilter = new IntentFilter(BaseApplication.VERIFYFAILT);
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
                        getSellerList(true);
                    }
                },800);
            }

            @Override
            public void onLoadMore() {
                listview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getSellerList(false);
                    }
                },800);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                new AlertView.Builder().setContext(activity)
                        .setStyle(AlertView.Style.ActionSheet)
                        .setTitle("审核")
                        .setMessage(null)
                        .setCancelText("取消")
                        .setDestructive("通过审核")
                        .setOthers(null)
                        .setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(Object o, int position) {
                                if(position!=-1) {
                                    verify(list.get(i - 1).getId() + "", 1, i - 1);
                                }

                            }
                        })
                        .build()
                        .show();
            }
        });
    }

    private void getSellerList(final boolean isRefresh){
        if(isRefresh){
            pageIndex = 1;
        }else{
            pageIndex++;
        }
        String path = HttpPath.getPath(HttpPath.GETCELLERLIST);
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
                Type type = new TypeToken<JsonModel<List<SellerEntity>>>(){}.getType();
                JsonModel<List<SellerEntity>> jsonModel = GsonUtil.GsonToBean(result, type);
                if(jsonModel.isSuccess()){
                    if(isRefresh){
                        list.clear();
                        if(jsonModel.hasData()){
                            list = jsonModel.getDataList();
                            adapter = new SellerAdapter(activity, list);
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
     * 商家审核
     * @param sellerID 商家ID
     * @param type 1通过审核
     * @param position 第几个
     */
    private void verify(String sellerID, int type, final int position){
        String path = HttpPath.getPath(HttpPath.VERIFY);
        RequestParams params = HttpUtil.params(path);
        params.addBodyParameter("uid", entity.getId()+"");
        params.addBodyParameter("tockens", entity.getTocken());
        params.addBodyParameter("operid", sellerID);
        params.addBodyParameter("type", "" + type);
        HttpUtil.http().post(params, new RequestCallBack<String>(activity){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                LogUtil.d(result);
                Type type = new TypeToken<ResultEntity>(){}.getType();
                ResultEntity resultEntity = GsonUtil.GsonToBean(result, type);
                if(resultEntity.isSuccee()){
                    ToastUtil.showShort("通过审核成功");
                    //移除当前位置
                    adapter.removeItem(position);
                    Intent intent = new Intent(BaseApplication.VERIFYSUCCESSS);
                    //更新已通过列表
                    activity.sendBroadcast(intent);
                }else{
                    ToastUtil.showShort("操作失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
//                ToastUtil.showShort(ex.getMessage());
            }
        });
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(BaseApplication.VERIFYFAILT)){
                LogUtil.d("刷新未通过列表3333333333");
                getSellerList(true);
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
