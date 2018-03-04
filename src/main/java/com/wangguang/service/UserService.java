package com.wangguang.service;

import com.wangguang.entity.User;

import java.util.List;

/**
 * Service - 商家信息
 *
 * @author peakren
 * @since 2017-10-29
 */
public interface UserService {


    User findById(Integer id);
    User save(String name);
    List<User> findAll();


}

