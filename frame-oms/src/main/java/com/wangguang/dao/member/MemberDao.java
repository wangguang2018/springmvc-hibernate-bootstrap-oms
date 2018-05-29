package com.wangguang.dao.member;

import com.wangguang.entity.member.Member;
import com.wangguang.model.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Dao - 会员
 */
public interface MemberDao extends BaseDao<Member, Integer> {


	@Query("select a from Member a where a.mobile like ?1 and a.flag = 1")
	Page<Member> findMembertByMobile(String name, Pageable pageable);



	@Deprecated
	@Query("select a from Member a WHERE a.flag = 1")
	List<Member> findMember();

	/**
	 * 根据手机号码查询会员账号
	 *
	 * @param mobile 手机号码
	 * @return 会员账号
	 */
	@Query("select a from Member a where a.mobile = :mobile and a.flag = 1 and a.agentId = :agentId")
	Member findMemberByMobile(@Param("mobile") String mobile, @Param("agentId") Integer agentId);



	/**
	 * 根据手机号码查询会员账号
	 *
	 * @param oauthId 认证ID
	 * @return 会员账号
	 */
	@Query("select a from Member a where a.oauthId = :oauthId and a.flag = 1 and a.agentId = :agentId")
	Member findMemberByOauthId(@Param("oauthId") String oauthId, @Param("agentId") Integer agentId);

	/**
	 * 根据微信用户惟一标志查看用户信息
	 *
	 * @param openid 微信用户惟一标志
	 * @return 会员用户信息
	 */
	@Query("select a from Member a where a.openId = ?1 and a.flag = 1")
	Member findMemberByWxOpenId(String openid);


	/**
	 * 根据玩吧用户惟一标志和平台来查找用户
	 *
	 * @param openid 微信用户惟一标志
	 * @return 会员用户信息
	 */
	@Query("select a from Member a where a.openId = ?1 and a.platform=?2 and a.flag = 1")
	Member findMemberByOpenIdAndPlatm(String openid, byte platform);

	/**
	 * 更新会员头像
	 *
	 * @param memberId 会员编号
	 * @param avatar 头像
	 */
	@Modifying
	@Query("update Member a set a.avatar = ?2 where a.id = ?1 and a.flag = 1")
	void updateAvatar(Integer memberId, String avatar);

	/**
	 * 更新会员积分
	 *
	 * @param memberId 会员便阿訇
	 * @param points 积分
	 */
	@Modifying
	@Query("UPDATE Member a SET a.points = a.points + ?2 WHERE a.id = ?1 and a.flag = 1")
	void updatePoinst(Integer memberId, Integer points);

	/**
	 * 查询有效用户
	 * @param startId
	 * @param endId
	 * @return
	 */
	@Query("select a from Member a where a.id > ?1 and a.id <= ?2 and a.status = 1")
	List<Member> findEffectiveMembers(Integer startId, Integer endId);


	@Query("select a from Member a where a.status = 1 and a.flag = 1")
	List<Member> findAllEffectiveMembers();

	@Modifying
	@Query("update Member a set a.points = (a.points + :point) where a.status = 1 and a.flag = 1")
	void changeTotalPoints(@Param("point") Integer point);

	/**
	 * 用户积分 为0以下  就设置为 0
     */
	@Modifying
	@Query("update Member a set a.points = 0 where a.status = 1 and a.flag = 1 and a.points < 0")
	void updateMemberPoints();

	/**
	 * 根据手机号查询用户数量
	 *
	 * @param mobile 手机号码
	 * @return 用户数量
	 */
	@Query("SELECT COUNT(a) FROM Member a WHERE a.flag = 1 AND a.mobile = ?1")
	Integer countByMobile(String mobile);

	/**
	 * 更新游戏币
	 * @param memberId
	 * @param money
	 */
	@Modifying
	@Query("update Member a set a.money = ?2 where a.flag = 1 and a.id = ?1")
	void updateMoney(Integer memberId, BigDecimal money);

	@Query("select count(m) from Member m where m.inviteCode = ?1")
	Long countByInviteCode(String inviteCode);

	@Query("select m from Member m where m.inviteCode = ?1")
	Member findByInviteCode(String inviteCode);

	@Query("select count(m) from Member m where m.registerTime >= ?1 and m.flag = 1")
	Long countByRegisterTime(Date date);

	@Query("select count(m) from Member m where m.registerTime >= ?1 and m.agentId = ?2 and m.flag = 1")
	Long countByRegisterTime(Date date, Integer agentId);

	@Query("select count(m) from Member m where m.agentId = ?1 and m.flag = 1")
	Long countMember(Integer agentId);

	@Query("select count(m) from Member m where m.flag = 1")
	Long countMember();

	@Query("select count(m) from Member m where m.flag = 1 and m.channelId = ?2 and DATE_FORMAT(m.registerTime,'%Y-%m-%d') = DATE_FORMAT(?1,'%Y-%m-%d')")
	Long countChannelMemberByDate(Date registerDate, Integer channelId);

}
