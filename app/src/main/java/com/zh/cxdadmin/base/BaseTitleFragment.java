package com.zh.cxdadmin.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.reflect.TypeToken;
import com.zh.cxdadmin.R;
import com.zh.cxdadmin.config.HttpPath;
import com.zh.cxdadmin.entity.ResultEntity;
import com.zh.cxdadmin.entity.UserInfoEntity;
import com.zh.cxdadmin.http.HttpUtil;
import com.zh.cxdadmin.http.RequestCallBack;
import com.zh.cxdadmin.ui.LoginActivity;
import com.zh.cxdadmin.utils.DbUtils;
import com.zh.cxdadmin.utils.DialogUtils;
import com.zh.cxdadmin.utils.GsonUtil;
import com.zh.cxdadmin.utils.ToastUtil;

import org.xutils.http.RequestParams;

import java.lang.reflect.Type;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by win7 on 2016/11/27.
 */

public class BaseTitleFragment extends BaseFragment {
    private UserInfoEntity entity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        entity = DbUtils.getInstance().getPersonInfo();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add(Menu.NONE , Menu.FIRST + 1 , 0, "注销").setIcon(R.drawable.ic_power_settings_new_white_24dp)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case Menu.FIRST + 1:
                DialogUtils.showProgress(activity);
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 注销用户
     */
    private void logout() {
        String path = HttpPath.getPath(HttpPath.LOGINOUT);
        RequestParams params = HttpUtil.params(path);
        params.addBodyParameter("uid", entity.getId()+"");
        params.addBodyParameter("tockens", entity.getTocken());
        HttpUtil.http().post(params, new RequestCallBack<String>(activity) {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Type type = new TypeToken<ResultEntity>() {
                }.getType();
                ResultEntity resultEntity = GsonUtil.GsonToBean(result, type);
                if (resultEntity.isSuccee(activity)) {
                    JPushInterface.setAlias(activity, "", new TagAliasCallback() {
                        @Override
                        public void gotResult(int i, String s, Set<String> set) {
                            DialogUtils.stopProgress(activity);
//                            ToastUtil.showShort("注销成功");
                            Intent intent1 = new Intent(activity, LoginActivity.class);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent1);
                            activity.finish();
                        }
                    });
                } else {
                    DialogUtils.stopProgress(activity);
                    ToastUtil.showShort(resultEntity.getError_desc());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                DialogUtils.stopProgress(activity);
//                ToastUtil.showShort(ex.getMessage());
            }
        });
    }
}
