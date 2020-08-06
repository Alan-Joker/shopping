package com.example.shopping.dao;

import com.example.shopping.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface UserMapper {

   // User findAllByUsername(String username,String password);
    User findAllByUsername(User user);

    void saveUser(User user);

    User findAllByName(String username);

    Integer updateById(@Param("telephone") String telephone, @Param("email") String email, @Param("userId") Integer userId);

    void updatePassword(@Param("userId") Integer userId,@Param("password") String password);

    List<User> findAll();

    void deleteById(Integer userId);

    User findById(Integer id);
}