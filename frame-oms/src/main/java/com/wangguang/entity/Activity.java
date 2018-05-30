package com.wangguang.entity;


import com.wangguang.entity.common.BaseEntity;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Entity -活动
 *
 * @author Demon
 * @since 24/01/2018
 */
@Entity
public class Activity extends BaseEntity {

    private static final long serialVersionUID = 2246705243888049159L;

    /**
     * 代理商编号
     */
    private Integer agentId;

    /**
     * 参与人数
     */
    private Integer joinCount;

    /**
     * 主标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 活动内容
     */
    private String content;

    /**
     * 活动封面图
     */
    private String img;

    /**
     * 奖金
     */
    private BigDecimal bonus;

    /**
     * 开始时间
     */
    private  Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 活动类型1:集五福活动（预留字段）
     * @see com.wangguang.model.enums.ActivityEnum
     */
    private Integer type;


    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getJoinCount() {
        return joinCount;
    }

    public void setJoinCount(Integer joinCount) {
        this.joinCount = joinCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
