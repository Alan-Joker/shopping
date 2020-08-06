package com.example.shopping.service;

import com.example.shopping.Entity.User;

import java.util.List;

public interface UserService {
    //查询所有用户信息
    List<User> findAll();

    void deleteById(Integer userid);
}
