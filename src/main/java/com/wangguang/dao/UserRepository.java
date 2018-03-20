/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.wangguang.dao;

import com.wangguang.common.BaseDao;
import com.wangguang.entity.sys.User;
import org.springframework.data.jpa.repository.Query;

/**
 * Dao - 系统用户
 */
public interface UserRepository extends BaseDao<User, Integer> {

	/**
	 * 根据登陆帐号查询用户
	 *
	 * @param account 登陆帐号
	 * @return 用户信息
	 */
	@Query("SELECT a FROM User a WHERE a.account = ?1 AND a.deleted = FALSE")
	User findByAccount(String account);
	/**
	 * 根据手机号码查询用户
	 *
	 * @param mobile 手机号码
	 * @return 用户信息
	 */
	/*@Query("SELECT a FROM User a WHERE a.phone = ?1 AND a.deleted = FALSE")
	User findByMobile(String mobile);*/

	/**
	 * 根据邮箱查询用户
	 *
	 * @param email 邮箱
	 * @return 用户信息
	 */
	/*@Query("SELECT a FROM User a WHERE a.email = ?1 AND a.deleted = FALSE")
	User findByEmail(String email);*/
}
