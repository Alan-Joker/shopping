package com.example.shopping.service.impl;

import com.example.shopping.Entity.Favorite;
import com.example.shopping.Entity.FavoriteKey;
import com.example.shopping.dao.FavoriteMapper;
import com.example.shopping.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    public void addFavorite(Favorite favorite) {
        favoriteMapper.addFavorite(favorite.getUserid(),favorite.getGoodsid(),favorite.getCollecttime());
    }

    @Override
    public void delete(FavoriteKey favoriteKey) {
        favoriteMapper.delete(favoriteKey);
    }

    @Override
    public Integer findById(FavoriteKey favoriteKey) {
        return favoriteMapper.findById(favoriteKey);
    }

    @Override
    public List<Integer> findGoodId(Integer userId)
    {
        return favoriteMapper.findGoodId(userId);
    }


}
