package com.wangguang.dao;


import com.wangguang.model.BaseDao;
import com.wangguang.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;


public interface OrderDao extends BaseDao<Order,Integer> {

    Order findByOrderSn(String orderSn);

    Page<Order> findByMemberId(Integer memberId, Pageable pageable);

    @Query(value = "update Order o set o.expressNo = ?1,o.expressTime = ?2,o.type = ?3,o.status = ?4 where o.orderSn = ?5")
    @Modifying
    void updateOrOrderByExpress(String expressNo, Date expressTime, byte type, byte status, String orderSn);


    @Query(value = "update Order o set o.expressNo = ?1,o.expressTime = ?2,o.type = ?3,o.status = ?4,o.expressName= ?5 where o.orderSn = ?6")
    @Modifying
    void updateOrderExpress(String expressNo, Date expressTime, byte type, byte status, String expressName, String orderSn);
}
