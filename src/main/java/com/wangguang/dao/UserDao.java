package com.wangguang.dao;

import com.wangguang.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

public interface UserDao extends JpaRepository<User, Integer> {

    User findById(Integer id);
}
