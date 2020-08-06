package com.example.shopping.service;

import com.example.shopping.Entity.Address;
import com.example.shopping.Entity.Order;
import com.example.shopping.Entity.Orderitem;

import java.util.List;

public interface OrderService {

    void insert(Order order);

    void insertOrderItem(Orderitem orderitem);

    List<Order> findAll(Integer userId);

    List<Orderitem> findAllByOid(Integer orderid);

    List<Order> findAllByAdmin();

    Address findAddress(Integer addressid);

    void updateOrder(Order order);

    List<Order> findAllByAdminNore();

    Order findById(Integer orderid);

    void updateOrderByKey(Order order);

    List<Order> findAllByAdminCom();

    void deleteById(Integer orderid);
}
