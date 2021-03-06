/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.wangguang.dao;

import com.wangguang.model.BaseDao;
import com.wangguang.model.sys.Role;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Dao - 角色
 */
public interface RoleRepository extends BaseDao<Role, Integer> {

	@Query(value = "from Role where createUid = ?1 ")
	List<Role> findRoleListByUid(int uid);

	/**
	 * 查询所有角色
	 *
	 * @return 查询所有角色
	 */
	@Query(value = "from Role")
	List<Role> findRoleList();

}
