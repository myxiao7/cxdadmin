package com.zh.cxdadmin.entity;

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
    public boolean isSuccee(){
        if(message){
            return true;
        }else{
            return false;
        }
    }
}
