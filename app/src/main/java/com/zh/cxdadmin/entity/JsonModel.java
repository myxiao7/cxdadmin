package com.zh.cxdadmin.entity;

import android.app.Activity;
import android.content.Intent;

import com.zh.cxdadmin.R;
import com.zh.cxdadmin.config.SharedData;
import com.zh.cxdadmin.ui.LoginActivity;
import com.zh.cxdadmin.utils.DialogUtils;

import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * 基础数据模型
 * Created by win7 on 2016/11/23.
 */

public class JsonModel<T> {
    private int error_code;
    private String error_desc;
    private boolean message;
    private int page;
    private int record;
    private int rows;
    private int total;
    private T dataList;
//    private List<T> dataList;


    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_desc() {
        return error_desc;
    }

    public void setError_desc(String error_desc) {
        this.error_desc = error_desc;
    }

    public boolean isMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getDataList() {
        return dataList;
    }

    public void setDataList(T dataList) {
        this.dataList = dataList;
    }

    /**
     * 返回是否成功返回
     * @return
     */
    public boolean isSuccess(final Activity activity){
        if(message){
            return true;
        }else{
            if(error_code ==1){
                DialogUtils.showProgress(activity, R.string.logout);
                JPushInterface.setAlias(activity, "", new TagAliasCallback() {
                    @Override
                    public void gotResult(int i, String s, Set<String> set) {
                        DialogUtils.stopProgress(activity);
                        SharedData.saveUserPwd("");
                        Intent intent1 = new Intent(activity, LoginActivity.class);
                        activity.startActivity(intent1);
                        activity.finish();
                    }
                });
            }
        }
        return false;
    }

    /**
     * 是否有数据
     * @return
     */
    public boolean hasData(){
        if(dataList == null){
            return false;
        }
        if(dataList instanceof List && ((List)dataList).size() <= 0){
            return false;
        }
        return true;
    }
}
