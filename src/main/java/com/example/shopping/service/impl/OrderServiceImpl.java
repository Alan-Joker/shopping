package com.example.shopping.service.impl;

import com.example.shopping.Entity.Address;
import com.example.shopping.Entity.Order;
import com.example.shopping.Entity.Orderitem;
import com.example.shopping.dao.AddressMapper;
import com.example.shopping.dao.OrderMapper;
import com.example.shopping.dao.OrderitemMapper;
import com.example.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderitemMapper orderitemMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public void insert(Order order) {
        orderMapper.insert(order);
    }

    @Override
    public void insertOrderItem(Orderitem orderitem) {
        orderitemMapper.insert(orderitem);
    }

    @Override
    public List<Order> findAll(Integer userId) {
        return orderMapper.findAllById(userId);
    }

    @Override
    public List<Orderitem> findAllByOid(Integer orderid) {
        return orderitemMapper.findByOid(orderid);
    }

    @Override
    public List<Order> findAllByAdmin() {
        return orderitemMapper.findAllByAdmin();
    }

    @Override
    public Address findAddress(Integer addressid) {
        Address allByAid = addressMapper.findAllByAid(addressid);
        return allByAid;
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.updateOrder(order);
    }

    @Override
    public List<Order> findAllByAdminNore() {

        return orderMapper.findAllByAdminNore();
    }

    @Override
    public Order findById(Integer orderid) {
        return orderMapper.findAll(orderid);
    }

    @Override
    public void updateOrderByKey(Order order) {
        orderMapper.updateOrder(order);
    }

    @Override
    public List<Order> findAllByAdminCom() {
        return orderMapper.findAllByAdminCom();
    }

    @Override
    public void deleteById(Integer orderid) {
        orderMapper.deleteById(orderid);
    }
}
