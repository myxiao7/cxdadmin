package com.zh.cxdadmin.entity;

/**
 * Created by win7 on 2016/11/23.
 */

public class LoginEntity extends ResultEntity{
    private UserInfoEntity managerDTO;

    public UserInfoEntity getManagerDTO() {
        return managerDTO;
    }

    public void setManagerDTO(UserInfoEntity managerDTO) {
        this.managerDTO = managerDTO;
    }
}
