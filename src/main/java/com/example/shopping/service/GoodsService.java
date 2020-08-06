package com.example.shopping.service;

import com.example.shopping.Entity.Favorite;
import com.example.shopping.Entity.Goods;
import com.example.shopping.Entity.Imagepath;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface GoodsService {
    Integer addGoods(Goods goods);

    void addImagePath(Imagepath imagepath);

    List<Goods> findAll();

    void deleteById(Integer id);

    void update(Goods goods);

    Goods findAllById(Integer goodsId);

    List<Goods> findAllsById(Integer goodsId);

    List<String> findImagePath(Integer goodsId);

    void updateAid(Integer goodsid, Integer activityid);

    List<Goods> findAllByList(List<Integer> goodsid);

    //String findImagePath(Integer goodsId);
}
