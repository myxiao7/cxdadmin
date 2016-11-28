package com.zh.cxdadmin.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by win7 on 2016/11/23.
 */

public class OrderEntity implements Parcelable {

    /**
     * acceptdate    : 2016-11-10 17:11:12
     * appointment : 2016-11-11  16:00-18:00
     * avartar : http://wx.qlogo.cn/mmopen/uvTjxxWQv7QctExibr141ibUGD9scqiascicTQ1pfqBsgSFcuyicXTQ2DhPl3UBKODGZWllYR6WLqL23kgNYGr9mGzgVWpPTBMiaa1/0
     * carbrank : 大众
     * carcolor : 黑色
     * carno : 鲁B1234
     * carstyle : 迈腾
     * finishdate : 2016-11-11 09:18:30
     * location : 抚顺路16号
     * mobile : 213213123
     * name : zh
     * operator : 我
     * operavartar :
     * opmobile : 18562653050
     * orderamount : 1
     * orderdate : 2016-11-10 11:19:31
     * orderid : fcd3caa0484d4cb7819e0c86413331ef
     * remark : 123
     * servicetypename : 测试,
     */

    private String acceptdate;
    private String appointment;
    private String avartar;
    private String carbrank;
    private String carcolor;
    private String carno;
    private String carstyle;
    private String finishdate;
    private String location;
    private String mobile;
    private String name;
    private String operator;
    private String operavartar;
    private String opmobile;
    private int orderamount;
    private String orderdate;
    private String orderid;
    private String remark;
    private String servicetypename;

    public String getAcceptdate() {
        return acceptdate;
    }

    public void setAcceptdate(String acceptdate) {
        this.acceptdate = acceptdate;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

    public String getCarbrank() {
        return carbrank;
    }

    public void setCarbrank(String carbrank) {
        this.carbrank = carbrank;
    }

    public String getCarcolor() {
        return carcolor;
    }

    public void setCarcolor(String carcolor) {
        this.carcolor = carcolor;
    }

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    public String getCarstyle() {
        return carstyle;
    }

    public void setCarstyle(String carstyle) {
        this.carstyle = carstyle;
    }

    public String getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(String finishdate) {
        this.finishdate = finishdate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperavartar() {
        return operavartar;
    }

    public void setOperavartar(String operavartar) {
        this.operavartar = operavartar;
    }

    public String getOpmobile() {
        return opmobile;
    }

    public void setOpmobile(String opmobile) {
        this.opmobile = opmobile;
    }

    public int getOrderamount() {
        return orderamount;
    }

    public void setOrderamount(int orderamount) {
        this.orderamount = orderamount;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getServicetypename() {
        return servicetypename;
    }

    public void setServicetypename(String servicetypename) {
        this.servicetypename = servicetypename;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.acceptdate);
        dest.writeString(this.appointment);
        dest.writeString(this.avartar);
        dest.writeString(this.carbrank);
        dest.writeString(this.carcolor);
        dest.writeString(this.carno);
        dest.writeString(this.carstyle);
        dest.writeString(this.finishdate);
        dest.writeString(this.location);
        dest.writeString(this.mobile);
        dest.writeString(this.name);
        dest.writeString(this.operator);
        dest.writeString(this.operavartar);
        dest.writeString(this.opmobile);
        dest.writeInt(this.orderamount);
        dest.writeString(this.orderdate);
        dest.writeString(this.orderid);
        dest.writeString(this.remark);
        dest.writeString(this.servicetypename);
    }

    public OrderEntity() {
    }

    protected OrderEntity(Parcel in) {
        this.acceptdate = in.readString();
        this.appointment = in.readString();
        this.avartar = in.readString();
        this.carbrank = in.readString();
        this.carcolor = in.readString();
        this.carno = in.readString();
        this.carstyle = in.readString();
        this.finishdate = in.readString();
        this.location = in.readString();
        this.mobile = in.readString();
        this.name = in.readString();
        this.operator = in.readString();
        this.operavartar = in.readString();
        this.opmobile = in.readString();
        this.orderamount = in.readInt();
        this.orderdate = in.readString();
        this.orderid = in.readString();
        this.remark = in.readString();
        this.servicetypename = in.readString();
    }

    public static final Parcelable.Creator<OrderEntity> CREATOR = new Parcelable.Creator<OrderEntity>() {
        @Override
        public OrderEntity createFromParcel(Parcel source) {
            return new OrderEntity(source);
        }

        @Override
        public OrderEntity[] newArray(int size) {
            return new OrderEntity[size];
        }
    };
}
