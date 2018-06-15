package com.wangguang.dao.member;


import com.wangguang.model.BaseDao;
import com.wangguang.model.entity.member.MemberSummary;

/**
 * Dao -
 */
public interface MemberSummaryDao extends BaseDao<MemberSummary, Integer> {



    /**
     * 获取用户
     * @param memberId
     * @return
     */
    MemberSummary findByMemberId(Integer memberId);



}
