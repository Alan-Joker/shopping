package com.example.shopping.dao;

import com.example.shopping.Entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    //通过用户名密码查询管理员信息
    Admin findAll(Admin admin);
}