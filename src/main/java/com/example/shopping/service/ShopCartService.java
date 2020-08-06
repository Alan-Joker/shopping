package com.example.shopping.service;

import com.example.shopping.Entity.Shopcart;

import java.util.List;

public interface ShopCartService {
    Shopcart findById(Integer goodsid);

    List<Shopcart> findByUserId(Integer userId);

    void inset(Shopcart shopcart);

    void deleteById(Integer goodsid, Integer userId);

    void update(Shopcart shopcart);
}
