package com.wangguang.model.repositories;

import com.wangguang.model.BaseDao;
import com.wangguang.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends BaseDao<Product,Integer> {

    /**
     * 查询娃娃
     *
     * @param productId 娃娃编号
     * @return 娃娃
     */
    @Query("SELECT a FROM Product a WHERE a.flag = 1 AND a.id = ?1")
    Product findById(Integer productId);

    List<Product> findAllByFlag(byte flag);

    List<Product> findAllByFlagAndAgentId(byte flag, Integer agentId);

    /**
     * 分页查询可兑换的大娃娃商品
     *
     * @param pageable 分页参数
     * @return 商品
     */
    @Query("SELECT a FROM Product a WHERE a.flag = 1 AND a.agentId = ?1  AND a.type=3")
    Page<Product> findBigdoll(Integer agentId, Pageable pageable);

    List<Product> findAllByFlagAndAgentIdAndType(byte flag, Integer agentId, byte type);

    @Query("update Product p set p.flag = ?1 where p.id = ?2")
    @Modifying
    void updateProductFlag(byte flag, Integer id);
}
