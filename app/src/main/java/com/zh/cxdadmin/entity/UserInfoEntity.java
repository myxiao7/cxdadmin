package com.zh.cxdadmin.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by win7 on 2016/11/23.
 */
@Table(name = "UserInfoEntity")
public class UserInfoEntity {

    /**
     * companyId :
     * companyLevel : 0
     * companyName :
     * departmentId :
     * hireDate :
     * id : 4
     * isManager : 0
     * name :
     * password : chuxiaoding888
     * sfzh :
     * sjhm :
     * tocken : 0f7da37890aa4e41
     * username : chuxiaoding
     */
    @Column(name="ids", isId = true)
    private int ids;
    @Column(name = "companyId")
    private String companyId;
    @Column(name = "companyLevel")
    private int companyLevel;
    @Column(name = "companyName")
    private String companyName;
    @Column(name = "departmentId")
    private String departmentId;
    @Column(name = "hireDate")
    private String hireDate;
    @Column(name = "id")
    private int id;
    @Column(name = "isManager")
    private int isManager;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "sfzh")
    private String sfzh;
    @Column(name = "sjhm")
    private String sjhm;
    @Column(name = "tocken")
    private String tocken;
    @Column(name = "username")
    private String username;

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public int getCompanyLevel() {
        return companyLevel;
    }

    public void setCompanyLevel(int companyLevel) {
        this.companyLevel = companyLevel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsManager() {
        return isManager;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public String getTocken() {
        return tocken;
    }

    public void setTocken(String tocken) {
        this.tocken = tocken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
