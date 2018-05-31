package com.wangguang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangguang.model.BaseEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 充值记录
 */
@Entity
public class ChargeLog extends BaseEntity {
    /**
     * 充值金额
     */
    private BigDecimal price;
    /**
     * 游戏币
     */
    private BigDecimal money;
    /**
     * 1支付宝  2微信  3:玩吧安卓 4：玩吧IOS 5:9377
     * @see com.dm.model.enums.EnumChargeLogType
     */
    private byte type;

    private Integer buyType;
    /**
     * 0未支付 1已完成
     */
    private byte status;
    /**
     * 用户ID
     */
    private Integer memberId;

    //private Member member;

    private String orderSn;
    private String tradeSn;

    private Integer optionId;

    /**
     * 代理商ID
     */
    private Integer agentId;

    //private Agent agent;

    /**
     * 方式
     */
    private String typeStr;

    /***
     * 状态
     */
    private String statusStr;

    /**
     * 渠道ID
     */
    private Integer channelId;

    /**
     * 渠道商
     */
    //private Channel channel;



    @Transient
    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    @Transient
    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Column(name = "member_id")
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @Column(name = "status")
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Integer getBuyType() {
        return buyType;
    }

    public void setBuyType(Integer buyType) {
        this.buyType = buyType;
    }

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }*/

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getTradeSn() {
        return tradeSn;
    }

    public void setTradeSn(String tradeSn) {
        this.tradeSn = tradeSn;
    }


    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    @JsonIgnore
    @Column(name = "agent_id")
    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    /*@NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", insertable = false, updatable = false)
    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }*/


    @Column(name = "channel_id")
    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }


    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }*/
}
