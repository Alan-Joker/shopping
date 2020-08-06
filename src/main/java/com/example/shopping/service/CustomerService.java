package com.example.shopping.service;

import com.example.shopping.Entity.User;

public interface CustomerService {

     User findAllByUsername(User user);

    void saveUser(User user);

    User findAllByName(String username);

    void updateById(String telephone,String email,Integer userId);

    void updatePassword(Integer userId,String password);

    User findById(Integer id);
}
