package com.example.shopping.dao;

import com.example.shopping.Entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer goodsid);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsid);

    List<Goods> selectByPrimaryKeys(Integer goodsid);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> findAll();

    void updateAid(@Param("goodsid") Integer goodsid,@Param("activityid") Integer activityid);

    List<Goods> findAllByList(List<Integer> goodsid);
}