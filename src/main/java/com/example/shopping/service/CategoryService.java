package com.example.shopping.service;

import com.example.shopping.Entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findAllById(Integer category);

    Category findByName(String catename);

    void insert(Category catename);

    void deleteById(Integer id);

    void saveByid(Category category);
}
