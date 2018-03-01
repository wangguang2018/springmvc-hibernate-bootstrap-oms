package com.wangguang.service;

import com.wangguang.entity.User;

/**
 * Service - 商家信息
 *
 * @author peakren
 * @since 2017-10-29
 */
public interface UserService {

    public void save(User user);

    public User getUserById(int userId);


}

