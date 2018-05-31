package com.wangguang.entity.member;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangguang.model.BaseEntity;
import com.wangguang.model.GlobalConstant;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Entity - 会员
 *
 * @author xingkong1221
 * @since 2015-11-16
 */
@Entity
public class Member extends BaseEntity {

    private static final long serialVersionUID = -5515404539580470078L;

    /**
     * 别名ID
     */
    private String aliasId;

    /**
     * 积分
     */
    private Integer points = 0;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 状态
     *
     * @see com.wangguang.model.enums.EnumMemberStatus
     */
    private byte status;

    /**
     * 收货地址
     */
    private List<MemberAddress> memberAddresses;

    /**
     * 微信openid
     */
    private String openId;


    /**
     * 微信统一认证ID,APP和公众号合一
     */
    private String oauthId;

    /**
     * 微信用户
     */
    //private WxUser wxUser;

    /**
     * 会员资料
     */
    private MemberProfile profile;

    /**
     * 备注
     */
    private String remark;

    /**
     * 游戏币
     */
    private BigDecimal money;


    /**
     * 平台   @see EnumPlatform
     */
    private Byte platform;

    /**
     * 环信用户ID
     */
    private String hxId;
    /**
     * 环信用户密码
     */
    private String hxPwd;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 周卡到期时间
     */
    private Date weekExpireDate;

    /**
     * 月卡到期时间
     */
    private Date mouthExpireDate;

    /**
     * 代理商ID
     */
    private Integer agentId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 渠道商ID
     */
    private Integer channelId;


    /**
     *会员统计
     */
    private MemberSummary memberSummary;


    @OneToOne(mappedBy="member")
    public MemberSummary getMemberSummary() {
        return memberSummary;
    }

    public void setMemberSummary(MemberSummary memberSummary) {
        this.memberSummary = memberSummary;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取积分
     *
     * @return 积分
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * 设置积分
     *
     * @param points 积分
     */
    public void setPoints(Integer points) {
        this.points = points;
    }


    public Byte getPlatform() {
        return platform;
    }

    public void setPlatform(Byte platform) {
        this.platform = platform;
    }

    /**
     * 获取手机号码
     *
     * @return 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取头像地址
     *
     * @return 头像地址
     */
    public String getAvatar() {
        if (avatar == null || avatar.trim().equals("")) {
            return GlobalConstant.DEFALUT_HEAD_URL;
        }
        return avatar;
    }

    /**
     * 设置头像地址
     *
     * @param avatar 头像地址
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取密码
     *
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取密码盐
     *
     * @return 密码盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置密码盐
     *
     * @param salt 密码盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取注册时间
     *
     * @return 注册时间
     */
    @Column(name = "register_time")
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置注册时间
     *
     * @param registerTime 注册时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取会员状态
     *
     * @return 会员状态
     * @see com.wangguang.model.enums.EnumMemberStatus
     */
    public byte getStatus() {
        return status;
    }

    /**
     * 设置会员状态
     *
     * @param status 会员状态
     * @see com.wangguang.model.enums.EnumMemberStatus
     */
    public void setStatus(byte status) {
        this.status = status;
    }

    /**
     * 获取收货地址
     *
     * @return 收货地址
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    public List<MemberAddress> getMemberAddresses() {
        return memberAddresses;
    }

    /**
     * 设置收货地址
     *
     * @param memberAddresses 收货地址
     */
    public void setMemberAddresses(List<MemberAddress> memberAddresses) {
        this.memberAddresses = memberAddresses;
    }

    /**
     * 获取微信open_id
     *
     * @return 微信open_id
     */
    @Column(name = "openid")
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置微信open_id
     *
     * @param openId 微信open_id
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取微信用户信息
     *
     * @return 微信用户信息
     */
   /* @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "openid", insertable = false, updatable = false,
            referencedColumnName = "openid")
    public WxUser getWxUser() {
        return wxUser;
    }

    *//**
     * 设置微信用户信息
     *
     * @param wxUser 微信用户信息
     *//*
    public void setWxUser(WxUser wxUser) {
        this.wxUser = wxUser;
    }*/

    /**
     * 获取会员资料
     *
     * @return 会员资料
     */
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "member")
    public MemberProfile getProfile() {
        return profile;
    }

    /**
     * 设置会员资料
     *
     * @param profile 会员资料
     */
    public void setProfile(MemberProfile profile) {
        this.profile = profile;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getHxId() {
        return hxId;
    }

    public void setHxId(String hxId) {
        this.hxId = hxId;
    }

    public String getHxPwd() {
        return hxPwd;
    }

    public void setHxPwd(String hxPwd) {
        this.hxPwd = hxPwd;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    @JsonIgnore
    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getAliasId() {
        return aliasId;
    }

    public void setAliasId(String aliasId) {
        this.aliasId = aliasId;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }


    public Date getWeekExpireDate() {
        return weekExpireDate;
    }

    public void setWeekExpireDate(Date weekExpireDate) {
        this.weekExpireDate = weekExpireDate;
    }

    public Date getMouthExpireDate() {
        return mouthExpireDate;
    }

    public void setMouthExpireDate(Date mouthExpireDate) {
        this.mouthExpireDate = mouthExpireDate;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }
}