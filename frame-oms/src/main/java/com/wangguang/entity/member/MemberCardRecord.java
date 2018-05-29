package com.wangguang.entity.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangguang.entity.common.BaseEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity - 会员卡购买记录
 *
 * @author xingkong1221
 * @since 2015-11-16
 */
@Entity
public class MemberCardRecord extends BaseEntity {

    private static final long serialVersionUID = -5515404539580470078L;

    /**
     * member_id
     */
    private Integer memberId;

    /**
     * 会员
     */
    private Member member;


    /**
     *类型
     */
    private Integer type;

    /**
     *代理商id
     */
    private Integer agentId;

    //private Agent agent;

    /**
     * 每日领取金额
     */
    private Integer dayMoney;


    private Date expireDate;

    private Integer totalMoney;

    private String receiveRecord;



    @Column(name = "member_id")
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDayMoney() {
        return dayMoney;
    }

    public void setDayMoney(Integer dayMoney) {
        this.dayMoney = dayMoney;
    }


    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getReceiveRecord() {
        return receiveRecord;
    }

    public void setReceiveRecord(String receiveRecord) {
        this.receiveRecord = receiveRecord;
    }

    @JsonIgnore
    @Column(name = "agent_id")
    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

   /* @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", insertable = false, updatable = false)
    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }*/

    /**
     * 获取会员
     *
     * @return 会员
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    public Member getMember() {
        return member;
    }

    /**
     * 设置会员
     *
     * @param member 会员
     */
    public void setMember(Member member) {
        this.member = member;
    }
}