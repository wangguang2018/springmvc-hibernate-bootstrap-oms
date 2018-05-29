package com.wangguang.entity.member;

import com.wangguang.entity.common.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

/**
 * Entity - 会员统计相关数据
 *
 * @author xingkong1221
 * @since 2015-11-16
 */
@Entity
@Where(clause="flag = 1 ")
public class MemberSummary extends BaseEntity {

    private static final long serialVersionUID = -5515404539580470071L;


    private Integer memberId;

    /**
     * 会员
     */
    private Member member;


    private String chargeReward;


    private String grabSuccessReward;

    private String grabReward;

    private Integer grabCounts;

    private Integer grabSuccessCounts;

    private Integer chargeCounts;

    /**
     * 剩余碎片总数
     */
    private Integer fragmentCounts;

    private BigDecimal  chargeMoneys;

    /**
     * 消费总钻石数
     */
    private int consumeDiamonds;


    public int getConsumeDiamonds() {
        return consumeDiamonds;
    }

    public void setConsumeDiamonds(int consumeDiamonds) {
        this.consumeDiamonds = consumeDiamonds;
    }


    /**
     * 获取会员
     *
     * @return 会员
     */
    @OneToOne //JPA注释： 一对一 关系
    @JoinColumn(name="member_id" ,insertable=false, updatable=false)
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Column(name = "member_id")
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getChargeReward() {
        return chargeReward;
    }

    public void setChargeReward(String chargeReward) {
        this.chargeReward = chargeReward;
    }

    public String getGrabSuccessReward() {
        return grabSuccessReward;
    }

    public void setGrabSuccessReward(String grabSuccessReward) {
        this.grabSuccessReward = grabSuccessReward;
    }

    public Integer getGrabCounts() {
        return grabCounts;
    }

    public void setGrabCounts(Integer grabCounts) {
        this.grabCounts = grabCounts;
    }

    public Integer getGrabSuccessCounts() {
        return grabSuccessCounts;
    }

    public void setGrabSuccessCounts(Integer grabSuccessCounts) {
        this.grabSuccessCounts = grabSuccessCounts;
    }

    public Integer getChargeCounts() {
        return chargeCounts;
    }

    public void setChargeCounts(Integer chargeCounts) {
        this.chargeCounts = chargeCounts;
    }

    public BigDecimal getChargeMoneys() {
        return chargeMoneys;
    }

    public void setChargeMoneys(BigDecimal chargeMoneys) {
        this.chargeMoneys = chargeMoneys;
    }


    public String getGrabReward() {
        return grabReward;
    }

    public void setGrabReward(String grabReward) {
        this.grabReward = grabReward;
    }


    public Integer getFragmentCounts() {
        return fragmentCounts;
    }

    public void setFragmentCounts(Integer fragmentCounts) {
        this.fragmentCounts = fragmentCounts;
    }
}