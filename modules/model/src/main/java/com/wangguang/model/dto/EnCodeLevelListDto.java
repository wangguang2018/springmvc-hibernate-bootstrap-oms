package com.wangguang.model.dto;

import java.util.List;

public class EnCodeLevelListDto {


    /**
     * 机器id
     */
    private List<Integer> id;


    /**
     * masterExposure
     */
    private List<Integer> masterExposure;

    /**
     * slaveExposure
     */
    private List<Integer> slaveExposure;


    /**
     * 分辨率
     */
    private List<Integer> encodeLevel;


    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }

    public List<Integer> getMasterExposure() {
        return masterExposure;
    }

    public void setMasterExposure(List<Integer> masterExposure) {
        this.masterExposure = masterExposure;
    }

    public List<Integer> getSlaveExposure() {
        return slaveExposure;
    }

    public void setSlaveExposure(List<Integer> slaveExposure) {
        this.slaveExposure = slaveExposure;
    }

    public List<Integer> getEncodeLevel() {
        return encodeLevel;
    }

    public void setEncodeLevel(List<Integer> encodeLevel) {
        this.encodeLevel = encodeLevel;
    }
}
