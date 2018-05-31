package com.wangguang.entity.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangguang.entity.Agent;
import com.wangguang.model.BaseEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity - 会员卡
 *
 * @author xingkong1221
 * @since 2015-11-16
 */
@Entity
public class MemberCard extends BaseEntity {

    private static final long serialVersionUID = -5515404539580470078L;


    /**
     * 标题
     */
    private String title;

    /**
     *代理商id
     */
    private Integer agentId;

    private Agent agent;

    /**
     * short_desc
     */
    private String shortDesc;

    /**
     * 详细说明
     */
    private String description;


    /**
     * 充值金额
     */
    private BigDecimal price;

    /**
     * 立即到账游戏币
     */
    private Integer money;


    private Integer dayMoney;

    /**
     * 类型：1周卡 2：月卡
     * @see com.wangguang.model.enums.EnumMemberCard
     */
    private Integer type;

    /**
     * 标记
     * @see com.wangguang.model.enums.MemberCardMarkEnum
     */
    private Integer mark;


    /**
     * 排序
     */
    private Integer sort = 0;




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;

    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }




    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getDayMoney() {
        return dayMoney;
    }

    public void setDayMoney(Integer dayMoney) {
        this.dayMoney = dayMoney;
    }


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @JsonIgnore
    @Column(name = "agent_id")
    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", insertable = false, updatable = false)
    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }


    /**
     * 实体转DTO
     */
    /*public MemberCard convert(MemberCardDto card) {
        this.setAgentId(card.getAgentId());
        this.setDescription(card.getDescription());
        this.setDayMoney(card.getDayMoney());
        this.setShortDesc(card.getShortDesc());
        this.setMoney(card.getMoney());
        this.setPrice(card.getPrice());
        this.setType((int)card.getType());
        this.setTitle(card.getTitle());
        this.setMark((int)card.getMark());
        return this;
    }*/
}