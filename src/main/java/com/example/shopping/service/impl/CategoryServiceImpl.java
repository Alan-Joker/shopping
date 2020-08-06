package com.example.shopping.service.impl;

import com.example.shopping.Entity.Category;
import com.example.shopping.dao.CategoryMapper;
import com.example.shopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        List<Category> all = categoryMapper.findAll();
        return all;
    }

    @Override
    public Category findAllById(Integer category) {

        return categoryMapper.selectByPrimaryKey(category);

    }

    @Override
    public Category findByName(String catename) {
        return categoryMapper.findByName(catename);
    }

    @Override
    public void insert(Category catename) {
        categoryMapper.insertSelective(catename);
    }

    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void saveByid(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }
}
