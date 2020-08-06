package com.example.shopping.Entity;

public class Admin {
    private Integer adminId;

    private String adminName;

    private String password;

    public Integer getAdminid() {
        return adminId;
    }

    public void setAdminid(Integer adminid) {
        this.adminId = adminid;
    }

    public String getAdminname() {
        return adminName;
    }

    public void setAdminname(String adminname) {
        this.adminName = adminname == null ? null : adminname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}