package com.wangguang.dao;

import com.wangguang.entity.sys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findById(Integer id);
}
