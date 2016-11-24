package com.zh.cxdadmin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.reflect.TypeToken;
import com.zh.cxdadmin.R;
import com.zh.cxdadmin.base.BaseActivity;
import com.zh.cxdadmin.config.HttpPath;
import com.zh.cxdadmin.config.SharedData;
import com.zh.cxdadmin.entity.LoginEntity;
import com.zh.cxdadmin.http.HttpUtil;
import com.zh.cxdadmin.http.RequestCallBack;
import com.zh.cxdadmin.utils.DbUtils;
import com.zh.cxdadmin.utils.DialogUtils;
import com.zh.cxdadmin.utils.GsonUtil;
import com.zh.cxdadmin.utils.ToastUtil;


import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

import java.lang.reflect.Type;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by win7 on 2016/9/18.
 */
public class LoginActivity extends BaseActivity {
    @Bind(R.id.login_name_txt)
    EditText loginNameTxt;
    @Bind(R.id.login_pwd_txt)
    EditText loginPwdTxt;
    @Bind(R.id.login_forget_txt)
    TextView loginForgetTxt;
    @Bind(R.id.login_login_btn)
    Button loginLoginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if(!TextUtils.isEmpty(SharedData.getUserName()) && !TextUtils.isEmpty(SharedData.getUserPwd())){
            loginNameTxt.setText(SharedData.getUserName());
            loginPwdTxt.setText(SharedData.getUserPwd());
        }
    }

    @OnClick({R.id.login_login_btn, R.id.login_forget_txt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_login_btn:
                if(TextUtils.isEmpty(loginNameTxt.getText().toString())){
                    ToastUtil.showShort(R.string.login_phone_hint);
                    return;
                }
                if(TextUtils.isEmpty(loginPwdTxt.getText().toString())){
                    ToastUtil.showShort(R.string.login_pwd_hint);
                    return;
                }

                DialogUtils.showProgress(activity);
                login();
//                downloadFile("");
                break;
            case R.id.login_forget_txt:
               /* Intent intent3 = new Intent(activity, ForgetActivity.class);
                startActivity(intent3);*/
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        String url = HttpPath.getPath(HttpPath.LOGIN);
        RequestParams params = HttpUtil.params(url);
        params.addBodyParameter("uname", loginNameTxt.getText().toString());
        params.addBodyParameter("password", loginPwdTxt.getText().toString());
        HttpUtil.http().post(params, new RequestCallBack<String>(activity) {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                DialogUtils.stopProgress(activity);
                LogUtil.d(result);
                Type type = new TypeToken<LoginEntity>() {
                }.getType();
                LoginEntity entity = GsonUtil.GsonToBean(result, type);
                if (entity.isSuccee()) {
                    ToastUtil.showShort("登录成功");
                    //保存用户信息
                    DbUtils.getInstance().clearPersonInfo();
//                    entity.getOperatorDTO().setIspass(1);
                    DbUtils.getInstance().savePersonInfo(entity.getManagerDTO());
                    SharedData.saveUserName(loginNameTxt.getText().toString());
                    SharedData.saveUserPwd(loginPwdTxt.getText().toString());

                    JPushInterface.setAlias(activity, entity.getManagerDTO().getId()+"", new TagAliasCallback() {
                        @Override
                        public void gotResult(int i, String s, Set<String> set) {
                        LogUtil.d("JPushInterface code" + i + "userid" + s.toString());

                        }
                    });
                    //去首页
                    Intent intent = new Intent(activity, MainActivity.class);
                    startActivity(intent);
                    activity.finish();
                } else {
                    ToastUtil.showShort("登录失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                DialogUtils.stopProgress(activity);
                ToastUtil.showShort("网络错误");
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
