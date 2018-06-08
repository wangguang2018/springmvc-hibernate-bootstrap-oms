package com.wangguang.model.dto;

import com.google.common.collect.Sets;

import java.io.Serializable;
import java.util.Set;

/**
 * USER: douya
 * DATE: 2017-10-26
 */
public class RoomDto implements Serializable {

    private static final long serialVersionUID = 2329388701116716963L;

    private String machineSn; // 机器编号，每个机器都代表一个房间

    private Integer status; // 当前房间机器的状态 1：游戏中  2：游戏结束  0：空闲中

    private Integer online; //在线状态 1 在线 0：离线

    private Integer result; //最近一局游戏结束的结果  1：抓中  0：没抓中

    private Integer grabStatus; //抓取状态  0：未点击抓取  1：点击了抓取

    private Integer fixStatus; //维护状态 0正常  1机器自动判断维护  2人工设置为维护

    private String dollLogId;  //最近一句的记录ID

    private Long startTime; //最近一局游戏开始的时间

    private Integer memberId;  //当前房间正在进行中的玩家

    private String headUrl;  //当前房间正在进行中的玩家的头像

    private String nickname;    // 昵称

    private Integer frequency; // 连续成功频率，如果大于等于3次，则认为机器不正常,将机器下线

    private Set<Integer> memberIds= Sets.newHashSet(); //当前房间所有的玩家列表

    public RoomDto() {
    }

    public String getMachineSn() {
        return machineSn;
    }

    public void setMachineSn(String machineSn) {
        this.machineSn = machineSn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Set<Integer> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(Set<Integer> memberIds) {
        this.memberIds = memberIds;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getGrabStatus() {
        return grabStatus;
    }

    public void setGrabStatus(Integer grabStatus) {
        this.grabStatus = grabStatus;
    }

    public Integer getFixStatus() {
        return fixStatus;
    }

    public void setFixStatus(Integer fixStatus) {
        this.fixStatus = fixStatus;
    }

    public String getDollLogId() {
        return dollLogId;
    }

    public void setDollLogId(String dollLogId) {
        this.dollLogId = dollLogId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}
