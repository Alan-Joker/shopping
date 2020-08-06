package com.example.shopping.dao;

import com.example.shopping.Entity.Imagepath;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImagepathMapper {
    int deleteByPrimaryKey(Integer pathid);

    int insert(Imagepath record);

    int insertSelective(Imagepath record);

    Imagepath selectByPrimaryKey(Integer pathid);

    int updateByPrimaryKeySelective(Imagepath record);

    int updateByPrimaryKey(Imagepath record);

    //String findPath(Integer goodsId);
    List<String> findPath(Integer goodsId);
}