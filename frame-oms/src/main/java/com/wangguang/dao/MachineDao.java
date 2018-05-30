package com.wangguang.dao;


import com.wangguang.entity.Machine;
import com.wangguang.model.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MachineDao extends BaseDao<Machine,Integer> {

    @Query("SELECT m FROM Machine m WHERE m.flag = 1 AND m.sn = ?1")
    Machine findBySn(String sn);

    @Query("select m from Machine m where m.flag = 1")
    List<Machine> findAll();

    @Query("select m from Machine m where m.id in ?1")
    List<Machine> findByIds(Integer[] ids);

    /*@Query("select m from Machine m  left join m.agent  where m.flag = 1")
    List<Machine> getAll();*/

    @Query("select m from Machine m where m.flag = 1 and m.agentId = ?1")
    List<Machine> findAllByAgentId(Integer agentId);


    @Query("select m from Machine m where m.flag = 1 and m.mark = ?1")
    Machine getByMark(String mark);

    @Query("select m from Machine m where m.flag = 1 and m.agentId = ?1 and m.productId=1")
    Page<Machine> findAllPage(Integer agentId, Pageable pageable);

   /* @Query("select m from  TagMachine tm join tm.machine m  where m.flag = 1 and tm.tagId=?1 and m.productId=1  order by m.online desc, m.fixStatus asc, m.sort desc")
    Page<Machine> findByTagId(Integer type, Pageable pageable);*/


    @Query("select m from Machine m where m.flag = 1  and m.agentId = ?1 and m.productId=1")
    Page<Machine> findHomeRoom(Integer agentId, Pageable pageable);

    @Query("select count(m) from Machine m where m.flag = 1 and m.agentId = ?1")
    Long countMachine(Integer agentId);

    @Query("select count(m) from Machine m where m.flag = 1")
    Long countMachine();

    @Modifying
    @Query(value = "update Machine m set m.sort = :sort where m.id = :id ")
    public void updateSort(@Param("id") int id, @Param("sort") int sort);


    @Modifying
    @Query(value = "update Machine m set m.mark = :mark where m.id = :id ")
    public void updateMark(@Param("id") int id, @Param("mark") String mark);


    @Modifying
    @Query(value = "update Machine m set m.flag = :flag where m.id = :id ")
    public void updateFlag(@Param("id") int id, @Param("flag") byte flag);

    @Modifying
    @Query(value = "update Machine m set m.fixStatus = :val where m.id in :ids ")
    public void updateFixStatus(@Param("val") int val, @Param("ids") Integer[] ids);
}
