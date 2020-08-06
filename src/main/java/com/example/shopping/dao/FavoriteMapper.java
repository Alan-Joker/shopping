package com.example.shopping.dao;

import com.example.shopping.Entity.Favorite;
import com.example.shopping.Entity.FavoriteKey;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface FavoriteMapper {

    void addFavorite(@Param("uid") Integer uid, @Param("gid") Integer gid, @Param("date") Date date);

    void delete(FavoriteKey favoriteKey);

    Integer findById(FavoriteKey favoriteKey);

    List<Integer> findGoodId(Integer userId);
}
