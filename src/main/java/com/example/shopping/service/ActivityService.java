package com.example.shopping.service;

import com.example.shopping.Entity.Activity;

import java.util.List;

public interface ActivityService {
    List<Activity> findAll();

    void insert(Activity activity);

    void delete(Integer aid);

    Activity findById(Integer activityid);
}
