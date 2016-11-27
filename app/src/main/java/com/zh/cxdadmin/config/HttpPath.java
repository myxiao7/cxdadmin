package com.zh.cxdadmin.config;

/**
 * 接口地址
 * Created by win7 on 2016/9/18.
 */
public class HttpPath {
    public static final String HOST = "http://ghl.xunmime.com/chuXiaoDing/";
//    public static final String HOST = "http://d15801597i.imwork.net/chuXiaoDing/";
//    public static final String HOST = "http://139.224.41.12:8080/chuXiaoDing/";
    /**
     * 登录
     */
    public static final String LOGIN = "xtgl_users_appLogin.action";
    /**
     * 获取加盟商列表
     */
    public static final String GETCELLERLIST = "xtgl_operator_appQueryOper.action";
    /**
     * 审核加盟商
     */
    public static final String VERIFY = "xtgl_operator_appVerifyOper.action";
    /**
     * 派单
     */
    public static final String GIVEORDER = "chuXiaoDing_orders_appAssignOrder.action";
    /**
     * 获取订单信息
     */
    public static final String GETORDERLIST = "chuXiaoDing_orders_appQueryOrders.action";
    /**
     * 获取订单详情
     */
    public static final String GETORDERDETAIL = "chuXiaoDing_orders_appOrderView.action";
    /**
     * 符合派单条件加盟商信息列表
     */
    public static final String FINDSELLERLIST = "chuXiaoDing_orders_findAllAppOperators.action";
    /**
     * 加盟商派单操作
     */
    public static final String GIVEVERIFY = "xtgl_operator_updateIsAceptOrders.action";
    /**
     * 获取客户信息列表
     */
    public static final String GETCUSTOMERLIST = "xtgl_driverUsers_appQuery.action";
    /**
     * 注销
     */
    public static final String LOGINOUT = "xtgl_users_exsitLogin.action";

    /**
     * 获取接口路径
     *
     * @return
     */
    public static String getPath(String action) {
        String host = HttpPath.HOST;
        return host + action;
    }


}
