package com.wangguang.dto;

import java.math.BigDecimal;

public class StatisticDto {

    private String agentName;

    private Integer todayMember;

    private Integer totalMember;

    private  Integer totalMachine;

    private BigDecimal totalCharge;

    private BigDecimal monthCharge;


    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Integer getTodayMember() {
        return todayMember;
    }

    public void setTodayMember(Integer todayMember) {
        this.todayMember = todayMember;
    }

    public Integer getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(Integer totalMember) {
        this.totalMember = totalMember;
    }

    public Integer getTotalMachine() {
        return totalMachine;
    }

    public void setTotalMachine(Integer totalMachine) {
        this.totalMachine = totalMachine;
    }

    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }

    public BigDecimal getMonthCharge() {
        return monthCharge;
    }

    public void setMonthCharge(BigDecimal monthCharge) {
        this.monthCharge = monthCharge;
    }
}
