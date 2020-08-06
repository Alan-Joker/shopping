package com.example.shopping.Entity;

import java.util.Date;
import java.util.List;

public class Goods {
    private Integer goodId;

    private String goodsname;

    private Integer price;

    private Integer num;

    private Date uptime;

    private Integer category;

    private String detailcate;

    private Integer activityid;

    private String description;

    private List<String> imagePaths;

    private boolean fav;

    private Activity activity;

    private Float newPrice;

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Float getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Float newPrice) {
        this.newPrice = newPrice;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    public Integer getGoodsid() {
        return goodId;
    }

    public void setGoodsid(Integer goodId) {
        this.goodId = goodId;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getDetailcate() {
        return detailcate;
    }

    public void setDetailcate(String detailcate) {
        this.detailcate = detailcate == null ? null : detailcate.trim();
    }

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }


}