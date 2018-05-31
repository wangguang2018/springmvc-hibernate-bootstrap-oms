package com.wangguang.dao;

import com.wangguang.model.entity.Agent;
import com.wangguang.model.BaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgentDao extends BaseDao<Agent,Integer> {

    @Query("select a from Agent a where a.key = ?1 and a.flag = 1")
    Agent findByKey(String key);

    @Modifying
    @Query("update Agent a set a.status = ?1 where a.id = ?2")
    void updateStatus(byte status, Integer id);

    Agent findByAdminId(Integer adminId);

    @Query("select a from Agent a where a.flag = 1")
    List<Agent> findAll();

    @Query("select a.id from Agent a where a.flag = 1 and (a.id=?1 or a.pid=?1)")
    List<Integer> getSubIdsAndSelf(Integer pid);

    @Query("select a from Agent a where a.flag = 1 and (a.id=?1 or a.pid=?1)")
    List<Agent> getSubsAndSelf(Integer pid);


    @Query("select a from Agent a where a.flag = 1 and a.pid=0 ")
    List<Agent> getLevelOnes();
}
