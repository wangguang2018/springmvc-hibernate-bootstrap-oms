package com.wangguang.dao.member;


import com.wangguang.entity.member.MemberSummary;
import com.wangguang.model.BaseDao;

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
