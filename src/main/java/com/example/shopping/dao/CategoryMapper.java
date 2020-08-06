package com.example.shopping.dao;

import com.example.shopping.Entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer cateid);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer cateid);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    List<Category> findAll();

    Category findByName(String cateName);
}