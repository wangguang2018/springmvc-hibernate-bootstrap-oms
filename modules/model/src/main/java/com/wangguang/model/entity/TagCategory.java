package com.wangguang.model.entity;


import com.wangguang.model.BaseEntity;

import javax.persistence.Entity;

/**
 * 标签种类
 * @author wangguang
 */
@Entity
public class TagCategory extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
