package com.example.shopping.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Favorite extends FavoriteKey {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date collecttime;


    public Date getCollecttime() {
        return collecttime;
    }

    public void setCollecttime(Date collecttime) {
        this.collecttime = collecttime;
    }
}