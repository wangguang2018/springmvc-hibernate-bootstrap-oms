package com.wangguang.dao;

import com.wangguang.entity.Activity;
import com.wangguang.model.BaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ActivityDao extends BaseDao<Activity,Integer> {

    @Modifying
    @Query("update Activity a set a.bonus = a.bonus + ?2 where a.id = ?1")
    void addBonus(Integer id, BigDecimal bonus);


    @Query("select a from Activity a where a.flag = 1 and a.type=?1 and a.agentId = ?2 ")
    List<Activity> getByTypeAndAgentId(int type, int agentId);

}
