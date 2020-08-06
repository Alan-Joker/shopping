package com.example.shopping.dao;

import com.example.shopping.Entity.Indent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IndentMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(Indent record);

    int insertSelective(Indent record);

    Indent selectByPrimaryKey(Integer orderid);

    int updateByPrimaryKeySelective(Indent record);

    int updateByPrimaryKey(Indent record);
}