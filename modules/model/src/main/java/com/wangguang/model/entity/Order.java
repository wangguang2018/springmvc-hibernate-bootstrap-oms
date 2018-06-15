package com.wangguang.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangguang.model.BaseEntity;
import com.wangguang.model.entity.member.Member;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 订单
 */
@Entity
public class Order extends BaseEntity {
    /**
     * 订单号
     */
    private String orderSn;
    /**
     * 用户ID
     */
    private Integer memberId;
    /**
     * 0已发货 1未发货
     */
    private Byte status;
    /**
     * 发货时间
     */
    private Date expressTime;
    /**
     * 快递号
     */
    private String expressNo;

    /**
     * 收货地址
     */
    private String address;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 收货人手机号
     */
    private String mobile;

    /**
     * 代理商ID
     */
    private Integer agentId;

    /**
     * 快递类型
     */
    private Byte type;

    /**
     * 快递名称
     */
    private String expressName;

    private Member member;

    /**
     * 品名
     */
    private String productName;

    /**
     * 总数量
     */
    private int productNum;

    @Transient
    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    @Transient
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    private List<OrderProduct> orderProducts;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    @Column(name = "member_id")
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }


    public Date getExpressTime() {
        return expressTime;
    }

    public void setExpressTime(Date expressTime) {
        this.expressTime = expressTime;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }


    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @JsonIgnore
    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }
}
