package com.example.shopping.service.impl;

import com.example.shopping.Entity.User;
import com.example.shopping.dao.AddressMapper;
import com.example.shopping.dao.UserMapper;
import com.example.shopping.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.TabExpander;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findAllByUsername(User user) {

        //User allByUsername = userMapper.findAllByUsername(user.getUsername(),user.getPassword());
        User allByUsername = userMapper.findAllByUsername(user);
        //System.out.println(allByUsername);
        return allByUsername;
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public User findAllByName(String username) {
        User info = userMapper.findAllByName(username);
        return info;
    }

    @Override
    public void updateById(String telephone,String email,Integer userId) {
        userMapper.updateById(telephone,email,userId);
    }

    @Override
    public void updatePassword(Integer userId,String password) {
        userMapper.updatePassword(userId,password);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }


}
