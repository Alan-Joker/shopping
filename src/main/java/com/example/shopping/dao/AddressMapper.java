package com.example.shopping.dao;

import com.example.shopping.Entity.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    int deleteByPrimaryKey(Integer addressid);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer addressid);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    List<Address> findAllById(Integer id);

    Address findAllByAid(Integer addressid);
}