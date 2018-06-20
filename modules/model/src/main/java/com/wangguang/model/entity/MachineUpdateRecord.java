package com.wangguang.model.entity;

import com.wangguang.model.BaseEntity;

import javax.persistence.Entity;

/**
* author : wangguang
* desc   : 机器主板软件记录
**/
@Entity
public class MachineUpdateRecord extends BaseEntity {

    /**
     * 升级标题
     */
    private String title;

    /**
     * 版本号
     */
    private String versionCode;

    /**
     * 内容
     */
    private String content;

    /**
     * 升级链接
     */
    private String updateUrl;


    /**
     *机器
     */
    private int machineId;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}