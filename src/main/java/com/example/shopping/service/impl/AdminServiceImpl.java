package com.example.shopping.service.impl;

import com.example.shopping.Entity.Admin;
import com.example.shopping.dao.AdminMapper;
import com.example.shopping.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findAll(Admin admin) {
        Admin all = adminMapper.findAll(admin);
        return all;
    }
}
