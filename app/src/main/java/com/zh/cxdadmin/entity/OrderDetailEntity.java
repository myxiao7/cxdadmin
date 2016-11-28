package com.zh.cxdadmin.entity;

/**
 * Created by win7 on 2016/11/27.
 */

public class OrderDetailEntity {
private SellerEntity appOperatorDTO;
private OrderDetailBean ordersDTO;

    public SellerEntity getAppOperatorDTO() {
        return appOperatorDTO;
    }

    public void setAppOperatorDTO(SellerEntity appOperatorDTO) {
        this.appOperatorDTO = appOperatorDTO;
    }

    public OrderDetailBean getOrdersDTO() {
        return ordersDTO;
    }

    public void setOrdersDTO(OrderDetailBean ordersDTO) {
        this.ordersDTO = ordersDTO;
    }
}
