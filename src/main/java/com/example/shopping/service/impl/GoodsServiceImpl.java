package com.example.shopping.service.impl;

import com.example.shopping.Entity.Favorite;
import com.example.shopping.Entity.Goods;
import com.example.shopping.Entity.Imagepath;
import com.example.shopping.dao.FavoriteMapper;
import com.example.shopping.dao.GoodsMapper;
import com.example.shopping.dao.ImagepathMapper;
import com.example.shopping.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ImagepathMapper imagepathMapper;

    @Override
    public Integer addGoods(Goods goods) {
        goodsMapper.insertSelective(goods);
        return goods.getGoodsid();
    }

    @Override
    public void addImagePath(Imagepath imagepath) {
        imagepathMapper.insertSelective(imagepath);
    }

    @Override
    public List<Goods> findAll() {
        return goodsMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Goods goods) {
        goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public Goods findAllById(Integer goodsId) {

        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public List<Goods> findAllsById(Integer goodsId) {

        return goodsMapper.selectByPrimaryKeys(goodsId);
    }

//    @Override
//    public String findImagePath(Integer goodsId) {
//        return imagepathMapper.findPath(goodsId);
//
//    }
    @Override
    public List<String> findImagePath(Integer goodsId) {
        return imagepathMapper.findPath(goodsId);

    }

    @Override
    public void updateAid(Integer goodsid, Integer activityid) {
        goodsMapper.updateAid(goodsid,activityid);
    }

    @Override
    public List<Goods> findAllByList(List<Integer> goodsid) {
        return goodsMapper.findAllByList(goodsid);
    }


}
