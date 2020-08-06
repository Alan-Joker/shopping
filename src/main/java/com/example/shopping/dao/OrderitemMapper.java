package com.example.shopping.dao;

import com.example.shopping.Entity.Order;
import com.example.shopping.Entity.Orderitem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderitemMapper {
    int deleteByPrimaryKey(Integer itemid);

    int insert(Orderitem record);

    int insertSelective(Orderitem record);

    Orderitem selectByPrimaryKey(Integer itemid);

    int updateByPrimaryKeySelective(Orderitem record);

    int updateByPrimaryKey(Orderitem record);

    List<Orderitem> findByOid(Integer orderid);

    List<Order> findAllByAdmin();
}