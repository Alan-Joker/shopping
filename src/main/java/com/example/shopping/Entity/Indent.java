package com.example.shopping.Entity;

import java.util.Date;

public class Indent {
    private Integer orderid;

    private Integer userid;

    private Date ordertime;

    private Float oldprice;

    private Float newprice;

    private Boolean ispay;

    private Boolean issend;

    private Boolean isreceive;

    private Boolean iscomplete;

    private Integer addressid;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Float getOldprice() {
        return oldprice;
    }

    public void setOldprice(Float oldprice) {
        this.oldprice = oldprice;
    }

    public Float getNewprice() {
        return newprice;
    }

    public void setNewprice(Float newprice) {
        this.newprice = newprice;
    }

    public Boolean getIspay() {
        return ispay;
    }

    public void setIspay(Boolean ispay) {
        this.ispay = ispay;
    }

    public Boolean getIssend() {
        return issend;
    }

    public void setIssend(Boolean issend) {
        this.issend = issend;
    }

    public Boolean getIsreceive() {
        return isreceive;
    }

    public void setIsreceive(Boolean isreceive) {
        this.isreceive = isreceive;
    }

    public Boolean getIscomplete() {
        return iscomplete;
    }

    public void setIscomplete(Boolean iscomplete) {
        this.iscomplete = iscomplete;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }
}