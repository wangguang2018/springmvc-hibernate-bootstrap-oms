package com.wangguang.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangguang.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

/**
 *娃娃商品
 */
@Entity
public class Product extends BaseEntity {
    /**
     * 名称
     */
    private String name;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 图片 ;隔开
     */
    private String imgs;

    private Machine machine;

    private String winImg;
    /**
     * 代理商ID
     */
    private Integer agentId;

    /**
     * 一个娃娃折合多少个钻石
     */
    private Integer diamondRate;


    private String detailImg;


    /**
     * 类型
     *@see com.wangguang.model.enums.EnumProductType
     */
    private byte type;

    /**
     * 商品库存数量  0:表示无限
     */
    private int stock;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDetailImg() {
        return detailImg;
    }

    public void setDetailImg(String detailImg) {
        this.detailImg = detailImg;
    }

    public Integer getDiamondRate() {
        return diamondRate;
    }

    public void setDiamondRate(Integer diamondRate) {
        this.diamondRate = diamondRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    @OneToOne
    @JoinColumn(name = "id", insertable = false, updatable = false,
            referencedColumnName = "product_id")
    @JsonBackReference
    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public String getWinImg() {
        return winImg;
    }

    public void setWinImg(String winImg) {
        this.winImg = winImg;
    }

    @JsonIgnore
    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
}
