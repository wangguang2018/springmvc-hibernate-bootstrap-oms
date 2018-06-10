package com.wangguang.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 机器流设置
 * USER: douya
 * DATE: 2017-11-08
 */
public class MachineVideoDto implements Serializable {

    /**
     * 请求命令
     */
    protected String cmd;


    protected String vmcNo;


    private Integer openExposureSetting;
    /**
     * 主摄像头曝光
     */
    private Integer masterExposure;
    /**
     * 副摄像头曝光
     */
    private Integer slaveExposure;

    /**
     * 分辨率
     */
    private Integer encodeLevel;


    /**
     * 曝光选项
     * @see com.wangguang.model.enums.EnumexposureMode
     */
    private Integer exposureMode;


    /**
     * 主摄像头亮度(0-255)
     */
    private Integer masterBrightness;

    /**
     * 从摄像头亮度(0-255)
     */
    private Integer slaveBrightness;

    /**
     * 主摄像头饱和度(0-255)
     */
    private Integer masterSaturability;

    /**
     * 从摄像头饱和度(0-255)
     */
    private Integer slaveSaturability;

    /**
     *自定义分辨率宽度
     */
    private Integer width;

    /**
     *自定义分辨率高度
     */
    private Integer height;


    public Integer getExposureMode() {
        return exposureMode;
    }

    public void setExposureMode(Integer exposureMode) {
        this.exposureMode = exposureMode;
    }

    public Integer getMasterBrightness() {
        return masterBrightness;
    }

    public void setMasterBrightness(Integer masterBrightness) {
        this.masterBrightness = masterBrightness;
    }

    public Integer getSlaveBrightness() {
        return slaveBrightness;
    }

    public void setSlaveBrightness(Integer slaveBrightness) {
        this.slaveBrightness = slaveBrightness;
    }

    public Integer getMasterSaturability() {
        return masterSaturability;
    }

    public void setMasterSaturability(Integer masterSaturability) {
        this.masterSaturability = masterSaturability;
    }

    public Integer getSlaveSaturability() {
        return slaveSaturability;
    }

    public void setSlaveSaturability(Integer slaveSaturability) {
        this.slaveSaturability = slaveSaturability;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }


    @JsonProperty("vmc_no")
    public String getVmcNo() {
        return vmcNo;
    }

    public void setVmcNo(String vmcNo) {
        this.vmcNo = vmcNo;
    }


    public Integer getOpenExposureSetting() {
        return openExposureSetting;
    }

    public void setOpenExposureSetting(Integer openExposureSetting) {
        this.openExposureSetting = openExposureSetting;
    }

    public Integer getMasterExposure() {
        return masterExposure;
    }

    public void setMasterExposure(Integer masterExposure) {
        this.masterExposure = masterExposure;
    }

    public Integer getSlaveExposure() {
        return slaveExposure;
    }

    public void setSlaveExposure(Integer slaveExposure) {
        this.slaveExposure = slaveExposure;
    }

    public Integer getEncodeLevel() {
        return encodeLevel;
    }

    public void setEncodeLevel(Integer encodeLevel) {
        this.encodeLevel = encodeLevel;
    }
}
