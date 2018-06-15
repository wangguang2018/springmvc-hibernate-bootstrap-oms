package com.wangguang.dao.member;

import com.wangguang.model.BaseDao;
import com.wangguang.model.entity.member.MemberToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Dao - 会员接口凭证
 *
 * @author xingkong1221
 * @date 2015-12-01
 */
public interface MemberTokenDao extends BaseDao<MemberToken, Integer> {

    /**
     * 根据置换凭证查询凭证信息
     *
     * @param refreshToken 置换凭证
     * @return 凭证信息
     */
    @Query("select a from MemberToken a where a.refreshToken = :refreshToken and a.flag = 1")
    MemberToken findByRefreshToken(@Param("refreshToken") String refreshToken);

    /**
     * 查询凭证信息
     *
     * @param memberId 会员编号
     * @return 会员凭证信息
     */
    @Query("SELECT a FROM MemberToken a WHERE a.memberId = ?1")
    MemberToken findByMemberId(Integer memberId);
}
