package com.zh.cxdadmin.config;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.zh.cxdadmin.base.BaseApplication;


/**
 * 本地存储相关
 * Created by win7 on 2016/9/18.
 */
public class SharedData {
    /**
     * 服务器地址
     */
    public static final String HOST = "host";

    /**
     * 用户名
     */
    public static final String USERNAME = "username";

    /**
     * 密码
     */
    public static final String USERPWD = "userpwd";

    private static SharedPreferences sharedPreferences;

    public static SharedPreferences getShare() {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(BaseApplication.getInstance());

        }
        return sharedPreferences;
    }

    /**
     * 获取服务地址
     *
     * @return
     */
    public static String getHost() {
        String host = getShare().getString(HOST, null);
        return host;
    }

    /**
     * 保存服务地址
     *
     * @return
     */
    public static void saveHost(String host) {
        getShare().edit().putString(HOST, host).commit();
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public static String getUserName() {
        String host = getShare().getString(USERNAME, null);
        return host;
    }

    /**
     * 保存用户名
     *
     * @return
     */
    public static void saveUserName(String name) {
        getShare().edit().putString(USERNAME, name).commit();
    }

    /**
     * 获取密码
     *
     * @return
     */
    public static String getUserPwd() {
        String host = getShare().getString(USERPWD, null);
        return host;
    }

    /**
     * 保存密码
     *
     * @return
     */
    public static void saveUserPwd(String pwd) {
        getShare().edit().putString(USERPWD, pwd).commit();
    }
}
