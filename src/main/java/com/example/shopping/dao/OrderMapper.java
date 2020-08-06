package com.example.shopping.dao;

import com.example.shopping.Entity.Order;

import java.util.List;

public interface OrderMapper {
    int insert(Order order);

    List<Order> findAllById(Integer userId);

    void updateOrder(Order order);

    List<Order> findAllByAdminNore();

    Order findAll(Integer orderid);

    List<Order> findAllByAdminCom();

    void deleteById(Integer orderid);
}
