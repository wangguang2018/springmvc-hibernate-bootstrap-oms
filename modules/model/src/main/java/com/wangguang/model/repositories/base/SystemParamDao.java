package com.wangguang.model.repositories.base;



import com.wangguang.model.BaseDao;
import com.wangguang.model.sys.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SystemParamDao extends BaseDao<Param, Integer> {

	Param findSysParamByKey(String key);


	Param findSysParamByKeyAndAgentId(String key, Integer agentId);

	/**
	 * 更新系统参数值
	 *
	 * @param key 键值
	 * @param quantity 数量
	 */
	@Modifying
	@Query(value = "update sys_param set `value` = `value` + ?2 where `key` = ?1", nativeQuery = true)
	void increase(String key, Integer quantity);



	@Query("select p from Param p where p.agentId is null")
	List<Param> getinit();
}
