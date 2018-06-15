package com.wangguang.dao;


import com.wangguang.model.BaseDao;
import com.wangguang.model.entity.Tag;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagDao extends BaseDao<Tag,Integer> {

    @Query("select t from Tag t where t.status=1 and t.flag=1")
    List<Tag> findAll();

    @Query("select t from Tag t where t.agentId=?1 and t.status=1 and t.flag=1")
    List<Tag> findAllByAgentId(Integer agentId);

    @Modifying
    @Query("update  Tag m set m.flag=-1 where m.id=?1")
    void deleteById(Integer id);

}
