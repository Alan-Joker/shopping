package com.example.shopping.Entity;

import com.example.shopping.utils.DateUtil.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
    private Integer userId;

    private String username;

    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date regTime;

    private String regTimeStr;

    private String email;

    private String telephone;


    public String getRegTimeStr() {
        if(regTime!=null){
            regTimeStr = DateUtils.dateToString(regTime, "yyyy-MM-dd HH:mm");
        }
        return regTimeStr;
    }

    public void setRegTimeStr(String regTimeStr) {
        this.regTimeStr = regTimeStr;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", regTime=" + regTime +
                ", regTimeStr='" + regTimeStr + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}