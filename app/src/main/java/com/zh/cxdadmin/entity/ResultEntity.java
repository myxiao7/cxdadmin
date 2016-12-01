package com.zh.cxdadmin.entity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;

import com.zh.cxdadmin.R;
import com.zh.cxdadmin.config.SharedData;
import com.zh.cxdadmin.ui.LoginActivity;
import com.zh.cxdadmin.utils.DialogUtils;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by win7 on 2016/11/27.
 */

public class ResultEntity {

    /**
     * error_code : 0
     * error_desc :
     * message : true
     */

    private int error_code;
    private String error_desc;
    private boolean message;

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
    /**
     * 是否请求成功
     * @return
     */
    public boolean isSuccee(final Activity activity){
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

}
