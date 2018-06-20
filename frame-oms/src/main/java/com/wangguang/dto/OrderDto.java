package com.wangguang.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangguang.entity.ExcelColumn;
import com.wangguang.model.BaseEntity;
import com.wangguang.model.entity.member.Member;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class OrderDto{
    /**
     * 订单号
     */
    @ExcelColumn(colName = "订单号")
    private String orderSn;

    /**
     * 收货人
     */
    @ExcelColumn(colName = "收货人")
    private String consignee;

    /**
     * 收货人手机号
     */
    @ExcelColumn(colName = "收货人手机号")
    private String mobile;

    /**
     * 收货地址
     */
    @ExcelColumn(colName = "收货地址")
    private String address;

    /**
     * 品名
     */
    @ExcelColumn(colName = "品名")
    private String productName;


    /**
     * 总数量
     */
    @ExcelColumn(colName = "总数量")
    private int productNum;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }
}


