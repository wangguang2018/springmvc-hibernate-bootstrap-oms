package com.wangguang.dao;

import com.wangguang.entity.DollLog;
import com.wangguang.model.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface DollLogDao extends BaseDao<DollLog,Integer> {

    @Query("select d from DollLog d where d.status = 0 and d.flag=1 ")
    List<DollLog> findSuccessLogs();


    @Query("select d from DollLog d where d.memberId = ?1")
    Page<DollLog> findDollLogList(Integer memberId, Pageable pageable);


    @Query(value = "select * from dm_doll_log d where d.machine_id = ?1 and d.member_id = ?2 and d.status = 2 and d.create_time > ?3 ORDER BY d.create_time desc LIMIT 1",nativeQuery = true)
    DollLog findLastLog(Integer machineId, Integer memberId, Date date);


    @Modifying
    @Query("update DollLog d set d.exchangeStatus = ?2 where d.id = ?1 ")
    void updateExchangeStatusById(Integer id, byte exchangeStatus);


    /**
     * 获取用户为抓中的总记录数
     * @param memberId
     * @return
     */
    @Query("select count(d.id) from DollLog d where d.memberId=?1 and d.status=1" )
    Integer getGrabFailCounts(Integer memberId);


    /**
     * 获取用户为抓中的总记录数
     * @param memberId
     * @return
     */
    @Query("select count(d.id) from DollLog d where d.memberId=?1 " )
    Integer getGrabCounts(Integer memberId);

    /**
     * 统计抓取记录数量
     *
     * @param memberId 会员编号
     * @return 抓取记录数量
     */
    @Query("SELECT COUNT(a) FROM DollLog a WHERE a.memberId = ?1")
    int countByMemberId(Integer memberId);


    /**
     * 统计抓取记录数量
     *
     * @param memberId 会员编号
     * @param excludeId 排除编号
     * @return 抓取记录数量
     */
    @Query("SELECT COUNT(a) FROM DollLog a WHERE a.memberId = ?1 AND a.id <> ?2")
    int countByMemberId(Integer memberId, Integer excludeId);


    /**
     * 获取未兑换的娃娃总数
     * @param memberId
     * @return
     */
    @Query("select count(d) from DollLog d where d.memberId = ?1 and d.status = 0 and d.exchangeStatus = 0")
    long getDollNums(Integer memberId);


}
