package com.wangguang.service;


import com.wangguang.common.persistence.DynamicSpecifications;
import com.wangguang.common.persistence.SearchFilter;
import com.wangguang.dao.member.MemberDao;
import com.wangguang.dao.member.MemberProfileDao;
import com.wangguang.dao.member.MemberTokenDao;
import com.wangguang.model.BaseDao;
import com.wangguang.model.entity.member.Member;
import com.wangguang.model.entity.member.MemberProfile;
import com.wangguang.model.entity.member.MemberToken;
import com.wangguang.model.enums.EnumMoneyLogSource;
import com.wangguang.model.enums.EnumMoneyLogType;
import com.wangguang.model.repositories.JpaDao;
import com.wangguang.services.CommonService;
import com.wangguang.services.ExceptionCode;
import com.wangguang.web.JsonMap;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Service - 用户
 */
@Service
public class MemberService extends BaseService<Member, Integer> {

    private final Logger logger = LoggerFactory.getLogger(MemberService.class);

    private MemberDao memberDao;

    @Resource
    private CommonService commonService;

    @Resource
    private JpaDao jpaDao;

    @Resource
    private MemberProfileDao memberProfileDao;

    /*@Resource
    private LogMemberMoneyService logMemberMoneyService;*/

    /*@Resource
    private MemberCache memberCache;*/

    @Resource
    private MemberTokenDao memberTokenDao;

    @Resource
    @Override
    public void setBaseDao(BaseDao<Member, Integer> baseDao) {
        this.baseDao = baseDao;
        memberDao = (MemberDao) baseDao;
    }

//    /**
//     * 根据账号查询会员信息
//     *
//     * @param mobile 手机号码
//     * @return 会员信息
//     */
//    @Transactional(readOnly = true)
//    public Member findByAccount(String mobile) {
//        if (StringUtils.isBlank(mobile)) {
//            return null;
//        }
//        return memberDao.findMemberByMobile(mobile);
//    }

    /**
     * 保存会员
     *
     * @param member 会员信息
     * @return 会员信息
     */
    @Override
    @Transactional
    public Member save(Member member) {
        Timestamp currentTime = commonService.getCurrentTime();
        Member temp1 = null;
        //新建 用户
        if(null == member.getId())
        {
            MemberProfile memberProfile = memberProfileDao.findByMemberId(member.getId());
            member.setSalt(RandomStringUtils.randomAlphabetic(4));
            member.setPassword(DigestUtils.md5Hex(member.getPassword() + member.getSalt()));
            member.setUpdateTime(currentTime);
            member.setCreateTime(currentTime);
            member.setRegisterTime(currentTime);
            memberProfile.setCreateTime(currentTime);
            memberProfile.setUpdateTime(currentTime);
            temp1 = memberDao.save(member);
            memberProfile.setMemberId(temp1.getId());
            memberProfileDao.save(memberProfile);
        }
        //编辑用户
        else
        {
            Member dbMember = memberDao.findOne(member.getId());
            dbMember.setMobile(member.getMobile());
            dbMember.setUpdateTime(currentTime);
            dbMember.setStatus(member.getStatus());
            dbMember.setAvatar(member.getAvatar());
            dbMember.setRemark(member.getRemark());

            MemberProfile profile = member.getProfile();
            MemberProfile dbProfile = memberProfileDao.findByMemberId(dbMember.getId());
            dbProfile.setUpdateTime(currentTime);
            dbProfile.setRealName(profile.getRealName());
            dbProfile.setPlatform(profile.getPlatform());
            dbProfile.setNickname(profile.getNickname());
            dbProfile.setAppVersion(profile.getAppVersion());
            dbProfile.setGender(profile.getGender());
            dbProfile.setPlatform(profile.getPlatform());
            dbProfile.setAppVersion(profile.getAppVersion());


            memberProfileDao.save(dbProfile);
            if (member.getPassword() != null && !member.getPassword().equals(dbMember.getPassword())) {
                dbMember.setPassword(DigestUtils.md5Hex(member.getPassword() + dbMember.getSalt()));
            }
            temp1 = memberDao.save(dbMember);
        }
        return temp1;
    }


    /**
     * 查询会员
     *
     * @param id 会员编号
     * @return 会员信息
     */
    @Transactional(readOnly = true)
    public Member findMember(Integer id) {
        if (id == null) {
            return null;
        }
        Member member = memberDao.findOne(id);
        return member;
    }

    /**
     * 更新会员状态
     *
     * @param ids    编号
     * @param status 状态
     */
    @Transactional
    public void updateStatus(Integer[] ids, byte status) {
        Member member;
        if (ids != null && ids.length > 0) {
            for (Integer id : ids) {
                member = findMember(id);
                if (member == null) {
                    continue;
                }
                member.setStatus(status);

                memberDao.save(member);
            }
        }
    }

    /**
     * 根据搜索条件查找所有的会员
     *
     * @param params
     * @param sort
     * @return
     */
    @Transactional(readOnly = true)
    public List<Member> list(Map<String, Object> params, Sort sort) {
        Map<String, SearchFilter> filters = SearchFilter.parse(params);
        Specification<Member> spec = DynamicSpecifications.bySearchFilter(
                filters.values(),
                (Class<Member>) ((ParameterizedType) getClass()
                        .getGenericSuperclass()).getActualTypeArguments()[0]);
        return baseDao.findAll(spec, sort);
    }

    /**
     * 查询所有有效用户
     *
     * @return
     */
    @Transactional
    public List<Member> findAllEffectiveMembers() {
        List list = memberDao.findAllEffectiveMembers();
        return list;
    }

    /**
     * 增加用户游戏币
     */
    @Transactional
    public void addMoney(Integer memberId, BigDecimal money){
        Member member = memberDao.findOne(memberId);
        member.setMoney(member.getMoney().add(money));

        // 查询token信息，并更新缓存中的用户信息
        MemberToken memberToken = memberTokenDao.findByMemberId(memberId);
        //memberCache.updateMemberCache(memberToken.getAccessToken(), member);

        memberDao.save(member);
        //logMemberMoneyService.changeMoney(member,money, EnumMoneyLogType.Income, EnumMoneyLogSource.REFUND,"退款");
    }

    /**
     * 给用户添加钻石
     * @param memberId
     * @return
     */
    @Transactional
    public JsonMap addMemberMoney(Integer memberId, BigDecimal money){
        if(money.compareTo(BigDecimal.ZERO) < 1){
            return new JsonMap(ExceptionCode.RECHARGE_MONEY_ERROR);
        }
        synchronized (this){
            Member member = memberDao.findOne(memberId);
            if(member==null){
                return new JsonMap(ExceptionCode.DATA_ERROR);
            }
            member.setMoney(member.getMoney().add(money));
            // 查询token信息，并更新缓存中的用户信息
            MemberToken memberToken = memberTokenDao.findByMemberId(memberId);
           // memberCache.updateMemberCache(memberToken.getAccessToken(), member);
            memberDao.save(member);
            //logMemberMoneyService.changeMoney(member,money, EnumMoneyLogType.Income, EnumMoneyLogSource.PLATFORM_GIFT,EnumMoneyLogSource.PLATFORM_GIFT.name);
        }
        return  new JsonMap(0,"赠送成功");
    }

    /**
     * 获取一天的渠新增道商用户
     * @return
     */
    public Integer getNewChannelMemberOneDay(Date date,Integer channelId){
        return memberDao.countChannelMemberByDate(date,channelId).intValue();
    }
}
