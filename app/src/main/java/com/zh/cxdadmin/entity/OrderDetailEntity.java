package com.zh.cxdadmin.entity;

/**
 * Created by win7 on 2016/11/27.
 */

public class OrderDetailEntity {
private SellerEntity appOperatorDTO;
private OrderEntity ordersDTO;

    public SellerEntity getAppOperatorDTO() {
        return appOperatorDTO;
    }

    public void setAppOperatorDTO(SellerEntity appOperatorDTO) {
        this.appOperatorDTO = appOperatorDTO;
    }

    public OrderEntity getOrdersDTO() {
        return ordersDTO;
    }

    public void setOrdersDTO(OrderEntity ordersDTO) {
        this.ordersDTO = ordersDTO;
    }
}
