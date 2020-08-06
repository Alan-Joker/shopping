package com.example.shopping.service.impl;

import com.example.shopping.Entity.User;
import com.example.shopping.dao.UserMapper;
import com.example.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {

        return userMapper.findAll();
    }

    @Override
    public void deleteById(Integer userId) {
        userMapper.deleteById(userId);
    }
}
