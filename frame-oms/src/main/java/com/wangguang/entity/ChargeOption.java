package com.wangguang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangguang.model.BaseEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 充值选项
 */
@Entity
@Table(name = "dm_charge_option")
public class ChargeOption extends BaseEntity {
    /**
     * 充值金额
     */
    private BigDecimal price;
    /**
     * 游戏币
     */
    private BigDecimal money;
    /**
     * 描述
     */
    private String desc;

    private Integer sort;

    /**
     * @see com.wangguang.enums.EnumChargeMark
     */
    private Integer mark;

    /**
     * 代理商ID
     */
    private Integer agentId;



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

    @Column(name = "description")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }
}
