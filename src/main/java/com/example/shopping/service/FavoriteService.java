package com.example.shopping.service;

import com.example.shopping.Entity.Favorite;
import com.example.shopping.Entity.FavoriteKey;

import java.util.List;

public interface FavoriteService {
    void addFavorite(Favorite favorite);

    void delete(FavoriteKey favoriteKey);

    Integer findById(FavoriteKey favoriteKey);

    List<Integer> findGoodId(Integer userId);
}
