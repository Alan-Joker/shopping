package com.example.shopping.service.impl;

import com.example.shopping.Entity.Activity;
import com.example.shopping.dao.ActivityMapper;
import com.example.shopping.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> findAll() {
        return activityMapper.findAll();
    }

    @Override
    public void insert(Activity activity) {
        activityMapper.insert(activity);
    }

    @Override
    public void delete(Integer aid) {
        activityMapper.deleteByPrimaryKey(aid);
    }

    @Override
    public Activity findById(Integer activityid) {
        return activityMapper.findById(activityid);
    }
}
