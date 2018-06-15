package com.wangguang.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangguang.model.BaseEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * 娃娃机
 */
@Entity
public class Machine extends BaseEntity {

    private static final long serialVersionUID = -2058734289704803413L;
    /**
     * 机器编码设备号
     */
    private String sn;
    /**
     * 商品ID
     */
    private Integer productId;

    private Product product;

    /**
     * 0空闲 1游戏中 2关闭
     */
    private Byte status;

    /**
     * 即构房间号
     */
    private String liveRoomCode;

    /**
     * 直播流1号（声网）
     */
    private Integer liveRoomId1;
    /**
     * 直播流2号（声网）
     */
    private Integer liveRoomId2;

//    private LiveRoom liveRoom1;
//
//    private LiveRoom liveRoom2;
    /**
     * 聊天室房间ID（环信）
     */
    private String chatRoom;

    /**
     * 设为首页
     */
    private Integer isTop;

    /**
     * 维护状态 0：非维护 1:系统设为维护 2：人工设置维护
     *
     * 1. 连续抓中3次变为1
     */
    private Integer fixStatus;
    /**
     * 房间人数
     */
    private Integer memberCount;
    /**
     * 代理商ID
     */
    private Integer agentId;

    /**
     * 机器Ip
     */
    private String clientIp;

    /**
     * 机器版本号
     */
    private String versionCode;


    /**
     * 是否在线:0离线 1在线
     */
    private Integer online;

    private Agent agent;

    /**
     * 机器编号
     */
    private String mark;

    private MachineSet machineSet;

    /**
     * 排序
     */
    private int sort = 0;


    @OneToOne(mappedBy = "machine",fetch = FetchType.LAZY)
    public MachineSet getMachineSet() {
        return machineSet;
    }

    public void setMachineSet(MachineSet machineSet) {
        this.machineSet = machineSet;
    }



    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Column(name = "product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @JsonIgnore
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false,referencedColumnName = "id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public String getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(String chatRoom) {
        this.chatRoom = chatRoom;
    }

    public Integer getFixStatus() {
        return fixStatus;
    }

    public void setFixStatus(Integer fixStatus) {
        this.fixStatus = fixStatus;
    }

    @Column(name = "live_room1")
    public Integer getLiveRoomId1() {
        return liveRoomId1;
    }

    public void setLiveRoomId1(Integer liveRoomId1) {
        this.liveRoomId1 = liveRoomId1;
    }

    @Column(name = "live_room2")
    public Integer getLiveRoomId2() {
        return liveRoomId2;
    }

    public void setLiveRoomId2(Integer liveRoomId2) {
        this.liveRoomId2 = liveRoomId2;
    }

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "live_room1", insertable = false, updatable = false)
//    public LiveRoom getLiveRoom1() {
//        return liveRoom1;
//    }
//
//    public void setLiveRoom1(LiveRoom liveRoom1) {
//        this.liveRoom1 = liveRoom1;
//    }
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "live_room2", insertable = false, updatable = false)
//    public LiveRoom getLiveRoom2() {
//        return liveRoom2;
//    }
//
//    public void setLiveRoom2(LiveRoom liveRoom2) {
//        this.liveRoom2 = liveRoom2;
//    }


    public String getLiveRoomCode() {
        return liveRoomCode;
    }

    public void setLiveRoomCode(String liveRoomCode) {
        this.liveRoomCode = liveRoomCode;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }


    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }


    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    @JsonIgnore
    @Column(name = "agent_id")
    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", insertable = false, updatable = false)
    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }


    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }
}
