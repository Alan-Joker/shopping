package com.example.shopping.dao;

import com.example.shopping.Entity.Chat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper {
    int deleteByPrimaryKey(Integer chatid);

    int insert(Chat record);

    int insertSelective(Chat record);

    Chat selectByPrimaryKey(Integer chatid);

    int updateByPrimaryKeySelective(Chat record);

    int updateByPrimaryKey(Chat record);
}