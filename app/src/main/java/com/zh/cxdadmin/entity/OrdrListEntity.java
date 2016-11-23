package com.zh.cxdadmin.entity;

import java.util.List;

/**
 * Created by win7 on 2016/11/23.
 */

public class OrdrListEntity {

    /**
     * error_code : 0
     * error_desc :
     * message : true
     * page : 1
     * record : 26
     * rows : 2
     * total : 13
     */

    private int error_code;
    private String error_desc;
    private boolean message;
    private int page;
    private int record;
    private int rows;
    private int total;
    private List<OrderEntity> dataList;

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

    public List<OrderEntity> getDataList() {
        return dataList;
    }

    public void setDataList(List<OrderEntity> dataList) {
        this.dataList = dataList;
    }
}
