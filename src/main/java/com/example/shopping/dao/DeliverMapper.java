package com.example.shopping.dao;

import com.example.shopping.Entity.Deliver;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliverMapper {
    int deleteByPrimaryKey(Integer deliverid);

    int insert(Deliver record);

    int insertSelective(Deliver record);

    Deliver selectByPrimaryKey(Integer deliverid);

    int updateByPrimaryKeySelective(Deliver record);

    int updateByPrimaryKey(Deliver record);
}