package com.wangguang.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * USER: douya
 * DATE: 2017-11-08
 */
public class MachineSetDto implements Serializable {

    /**
     * 请求命令
     */
    protected String cmd;


    private String vmcNo;
    /**
     * 游戏模式
     */
    private Integer gameMode;
    /**
     * 强力电压
     */
    private String strongVoltage;

    /**
     * 弱力电压
     */
    private String smallVoltage;


    /**
     * 强转弱时间
     */
    private String changeTime;

    /**
     * 中奖概率
     */
    private Integer probability;

    /**
     * 到顶转弱抓力: 0关闭，1开启
     */
    private Byte changeWeak;


    public Byte getChangeWeak() {
        return changeWeak;
    }

    public void setChangeWeak(Byte changeWeak) {
        this.changeWeak = changeWeak;
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


    public Integer getGameMode() {
        return gameMode;
    }

    public void setGameMode(Integer gameMode) {
        this.gameMode = gameMode;
    }

    public String getStrongVoltage() {
        return strongVoltage;
    }

    public void setStrongVoltage(String strongVoltage) {
        this.strongVoltage = strongVoltage;
    }

    public String getSmallVoltage() {
        return smallVoltage;
    }

    public void setSmallVoltage(String smallVoltage) {
        this.smallVoltage = smallVoltage;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }
}
