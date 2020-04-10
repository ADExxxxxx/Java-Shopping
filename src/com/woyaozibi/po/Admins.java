package com.woyaozibi.po;

import java.util.ArrayList;
import java.util.Date;

public class Admins {
    private int adminid;
    private String adminame;
    private String adminpwd;
    private String status;
    private String remark;
    private Date admindate;

    public Admins(){}

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public String getAdminame() {
        return adminame;
    }

    public void setAdminame(String adminame) {
        this.adminame = adminame;
    }

    public String getAdminpwd() {
        return adminpwd;
    }

    public void setAdminpwd(String adminpwd) {
        this.adminpwd = adminpwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getAdmindate() {
        return admindate;
    }

    public void setAdmindate(Date admindate) {
        this.admindate = admindate;
    }
}
