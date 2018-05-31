package com.wangguang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangguang.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SignOption extends BaseEntity {
    private String desc;
    private int points;
    private Integer dayId;

    private Integer type;
    /**
     * 代理商ID
     */
    private Integer agentId;

    @Column(name = "description")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @JsonIgnore
    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
