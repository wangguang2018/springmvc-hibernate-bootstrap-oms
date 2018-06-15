package com.wangguang.model.entity;


import com.wangguang.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * 广告
 */
@Entity
public class Tag extends BaseEntity {

    private String tagName;
    /**
     *
     */
    private Integer status;

    private Integer agentId;

    private Integer tagCategoryId;

    public Integer getTagCategoryId() {
        return tagCategoryId;
    }

    public void setTagCategoryId(Integer tagCategoryId) {
        this.tagCategoryId = tagCategoryId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }
}
