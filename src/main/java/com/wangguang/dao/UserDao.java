package com.wangguang.dao;

import com.wangguang.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Mapper - 商家信息
 *
 * @author peakren
 * @since 2017-10-29
 */
@Repository
public interface UserDao {


    User selectByPrimaryKey(int userId);

    void insert(User user);

}