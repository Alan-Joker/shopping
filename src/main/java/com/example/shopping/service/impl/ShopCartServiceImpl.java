package com.example.shopping.service.impl;

import com.example.shopping.Entity.Shopcart;
import com.example.shopping.dao.ShopcartMapper;
import com.example.shopping.service.ShopCartService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private ShopcartMapper shopcartMapper;
    @Override
    public Shopcart findById(Integer goodsid) {

        return shopcartMapper.findById(goodsid);
    }

    @Override
    public List<Shopcart> findByUserId(Integer userId) {

        return shopcartMapper.findByUserId(userId);
    }

    @Override
    public void inset(Shopcart shopcart) {
        shopcartMapper.insert(shopcart);
    }

    @Override
    public void deleteById(@Param("goodsid") Integer goodsid, @Param("userId") Integer userId) {
        shopcartMapper.delete(goodsid,userId);
    }

    @Override
    public void update(Shopcart shopcart) {
        shopcartMapper.updateByPrimaryKey(shopcart);
    }
}
