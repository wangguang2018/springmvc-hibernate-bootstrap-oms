package com.wangguang.dao;

import com.wangguang.entity.ChargeLog;
import com.wangguang.model.BaseDao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ChargeLogDao extends BaseDao<ChargeLog,Integer> {

    ChargeLog findByOrderSn(String orderSn);

    ChargeLog findByTradeSn(String tradeSn);

    List<ChargeLog> findByMemberId(Integer memberId, Pageable pageable);

    @Query("select c from ChargeLog c where c.memberId=?1 and c.status=1")
    List<ChargeLog> findSuccessRecord(Integer memberId, Pageable pageable);

    @Query("select sum(c.price) from ChargeLog c where c.status = 1 and c.agentId = ?1")
    BigDecimal sumPrice(Integer agentId);

    /**
     * 获取用户充值记录数
     * @param memberId
     * @return
     */
    @Query("select count(c.id) from ChargeLog c where c.status=1 and c.memberId=?1")
    Integer getCount(Integer memberId);

    /**
     * 获取用户充值总钱数
     * @param memberId
     * @return
     */
    @Query("select sum(c.price) from ChargeLog c where c.status=1 and c.memberId=?1")
    Double getChargeMoneys(Integer memberId);

    @Query("select sum(c.price) from ChargeLog c where c.status = 1")
    BigDecimal sumPrice();

    @Query("select sum(c.price) from ChargeLog c where c.status = 1 and DATE_FORMAT(c.createTime,'%Y-%m-%d') = DATE_FORMAT(?1,'%Y-%m-%d')")
    BigDecimal sumPriceByDate(Date date);

    @Query("select sum(c.price) from ChargeLog c where c.status = 1 and c.agentId = ?2 and DATE_FORMAT(c.createTime,'%Y-%m-%d') = DATE_FORMAT(?1,'%Y-%m-%d')")
    BigDecimal sumPriceByDate(Date date, Integer agentId);

    @Query("select sum(c.price) from ChargeLog c where c.status = 1 and c.agentId = ?2 and c.createTime > ?1 " )
    BigDecimal agentSumPriceByDate(Date date, Integer agentId);



    /**
     * 判断用户充值次数
     * @param memberId
     * @return
     */
    @Query("select count(c) from ChargeLog c where c.status=1 and c.memberId=?1 and c.optionId = ?2 and c.buyType=?3")
    Integer checkBuyCounts(Integer memberId, Integer optionId, Integer buyType);



    /**
     * 判断用户是否是首冲
     * @param memberId
     * @return
     */
    @Query("select count(c) from ChargeLog c where c.status=1 and c.memberId=?1")
    Integer checkFirstBuy(Integer memberId);

    /**
     * 用户今日充值次数
     *
     * @param memberId 会员编号
     * @param date 日期
     * @return 充值次数
     */
    @Query("SELECT COUNT(c) FROM ChargeLog c WHERE c.status = 1 AND c.memberId = ?1 AND c.createTime >= ?2")
    int countDailyRecharge(Integer memberId, Date date);
}
