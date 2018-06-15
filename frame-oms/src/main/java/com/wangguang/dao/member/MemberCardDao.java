package com.wangguang.dao.member;

import com.wangguang.model.BaseDao;
import com.wangguang.model.entity.member.MemberCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Dao - 会员
 */
public interface MemberCardDao extends BaseDao<MemberCard, Integer> {

    /**
     * 根据会员卡类型获取每日增加的钻石
     *
     * @param type 类型
     */
    @Query("SELECT m.dayMoney FROM MemberCard m WHERE m.flag = 1 AND m.type = ?1 and m.agentId=?2")
    Integer getDayMoneyByType(Integer type, Integer agentId);



    /**
     * 根据会员卡类型获取每日增加的钻石
     *
     * @param type 类型
     */
    @Query("SELECT m FROM MemberCard m WHERE m.flag = 1 AND m.type = ?1 AND m.agentId=?2")
    MemberCard getMemberCard(Integer type, Integer agentId);

    /**
     *
     * 获取所有的会员卡
     *
     * @param
     */
    @Query("SELECT m FROM MemberCard m WHERE m.flag = 1 and m.agentId=?1")
    List<MemberCard> getMemberCards(Integer agentId);


	/**
	 *
	 *
	 * @param pageable 分页信息
	 * @return 会员卡信息
	 */
	@Query("select a from MemberCard a where  a.flag = 1")
	Page<MemberCard> findAll(Pageable pageable);


	/**
	 * 查询某类会员卡
	 * @param type
	 * @return
	 */
	@Query("select a from MemberCard a where   a.flag = 1 and type = :type and a.agentId= :agentId ")
    MemberCard findByTypeAndAgentId(@Param("type") int type, @Param("agentId") int agentId);



}
