package com.wangguang.dao;

import com.wangguang.entity.SignOption;
import com.wangguang.model.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SignOptionDao extends BaseDao<SignOption,Integer> {

    List<SignOption> findAll();

    List<SignOption> findByAgentId(Integer agentId);

    @Query("select o from SignOption o where o.dayId = ?1 and o.agentId = ?2 and o.flag = 1")
    SignOption findByAgentId(Integer dayId, Integer agentId);

    @Query("select o from SignOption o where o.agentId = ?1 and o.flag = 1")
    List<SignOption> findAllByAgentId(Integer agentId);

    @Query("select o from SignOption o where o.agentId = ?1 and o.flag=1 ")
    List<SignOption> getAllByAgentId(Integer agentId);

    @Query("select count(o) from SignOption o where o.agentId = ?1 and o.flag=1 ")
    Long getAgentSignCount(Integer agentId);
}
