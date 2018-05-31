package com.wangguang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangguang.model.BaseEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 抓娃娃记录
 */
@Entity
public class DollLog extends BaseEntity {
    /**
     * 视频地址
     */
    private String url;
    /**
     * 用户ID
     */
    private Integer memberId;

    /**
     * 用户
     */
    //private Member member;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 抓取价格
     */
    private BigDecimal productPrice;
    /**
     * 兑换状态 0未兑换 1已兑换
     */
    private Byte exchangeStatus;
    /**
     * 0 成功 1 失败 2 游戏中
     */
    private Byte status;
    /**
     * 申诉ID
     */
    private Integer appealId;

    /**
     * 娃娃图片
     */
    private String productImgs;

    /**
     * 娃娃ID
     */
    private Integer productId;

    /**
     * 娃娃
     */
    //private Product product;

    /**
     * 机器ID
     */
    private Integer machineId;

   // private Appeal appeal;

    /**
     * 代理商ID
     */
    private Integer agentId;

    //private Machine machine;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "member_id")
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Byte getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(Byte exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }


   /* @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false,referencedColumnName = "id")
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }*/

    @Column(name = "appeal_id")
    public Integer getAppealId() {
        return appealId;
    }

    public void setAppealId(Integer appealId) {
        this.appealId = appealId;
    }

    @Column(name="product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductImgs() {
        return productImgs;
    }

    public void setProductImgs(String productImgs) {
        this.productImgs = productImgs;
    }

    @Column(name = "machine_id")
    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    /*@NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appeal_id", insertable = false, updatable = false,referencedColumnName = "id")
    public Appeal getAppeal() {
        return appeal;
    }

    public void setAppeal(Appeal appeal) {
        this.appeal = appeal;
    }*/

    @JsonIgnore
    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    /*@NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id", insertable = false, updatable = false,referencedColumnName = "id")
    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }*/

    /*@OneToOne //JPA注释： 一对一 关系
    @JoinColumn(name="product_id" ,insertable=false, updatable=false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }*/
}
