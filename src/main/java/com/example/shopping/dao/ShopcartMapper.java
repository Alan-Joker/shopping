package com.example.shopping.dao;

import com.example.shopping.Entity.Shopcart;
import com.example.shopping.Entity.ShopcartKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopcartMapper {
    int deleteByPrimaryKey(ShopcartKey key);

    int insert(Shopcart record);

    int insertSelective(Shopcart record);

    Shopcart selectByPrimaryKey(ShopcartKey key);

    int updateByPrimaryKeySelective(Shopcart record);

    int updateByPrimaryKey(Shopcart record);

    Shopcart findById(Integer id);

    List<Shopcart> findByUserId(Integer userId);

    void delete(Integer goodsid, Integer userId);
}