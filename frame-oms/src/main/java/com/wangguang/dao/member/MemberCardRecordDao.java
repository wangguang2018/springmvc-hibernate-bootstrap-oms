package com.wangguang.dao.member;

import com.wangguang.entity.member.MemberCardRecord;
import com.wangguang.model.BaseDao;
import org.springframework.data.jpa.repository.Query;

/**
 * Dao - 会员账号信息
 *
 * @author xingkong1221
 * @since 2015-11-20
 */
public interface MemberCardRecordDao extends BaseDao<MemberCardRecord, Integer> {


    @Query("select m from MemberCardRecord m where m.memberId=?1 and m.type=?2 and m.expireDate>=Date(now()) and m.createTime<=Date(now())")
    MemberCardRecord getRecord(Integer memberId, Integer type);



}
