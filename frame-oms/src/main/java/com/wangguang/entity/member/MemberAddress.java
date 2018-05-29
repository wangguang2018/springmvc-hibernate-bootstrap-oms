package com.wangguang.entity.member;


import com.wangguang.entity.common.BaseEntity;

import javax.persistence.*;

/**
 * Entity - 会员收货地址
 *
 * @author xingkong1221
 * @since 2015-11-16
 */
@Entity
public class MemberAddress extends BaseEntity {

    private static final long serialVersionUID = -2108076528223780636L;

    /**
     * 会员id
     */
    private Integer memberId;

    /**
     * 地址
     */
    private String address;

    /**
     * 会员
     */
    private Member member;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 收获地址（街道地址）
     */
    private String street;


    /**
     * 省名称
     */
    private String provinceName;


    /**
     * 市名称
     */
    private String cityName;


    /**
     * 区名称
     */
    private String districtName;

    /**
     * 默认地址
     * <p>
     * 1:默认地址  0:非默认地址
     */
    private byte isDefault;

    /**
     * 获取会员编号
     *
     * @return 会员编号
     */
    @Column(name = "member_id")
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * 设置会员编号
     *
     * @param memberId 会员编号
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取会员
     *
     * @return 会员
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    public Member getMember() {
        return member;
    }

    /**
     * 设置会员
     *
     * @param member 会员
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * 获取收货人
     *
     * @return 收货人
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * 设置收货人
     *
     * @param consignee 收货人
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    /**
     * 获取手机号码
     *
     * @return 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取街道地址
     *
     * @return 街道地址
     */
    public String getStreet() {
        return street;
    }

    /**
     * 设置街道地址
     *
     * @param street 街道地址
     */
    public void setStreet(String street) {
        this.street = street;
    }


    /**
     * 获取省份名称
     *
     * @return 省份名称
     */
    @Column(name = "province_name")
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置省份名称
     *
     * @param provinceName 省份名称
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }


    /**
     * 获取城市名字
     *
     * @return 城市名字
     */
    @Column(name = "city_name")
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置城市名字
     *
     * @param cityName 城市名字
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    /**
     * 获取区名称
     *
     * @return 区名称
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * 设置区名称
     *
     * @param districtName 区名称
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * 获取默认地址标识
     * 0:非默认地址  1:默认地址
     *
     * @return 默认地址标识
     * @see com.wangguang.model.enums.EnumDefault
     */
    @Column(name = "is_default")
    public byte getIsDefault() {
        return isDefault;
    }

    /**
     * 设置默认地址标识
     *
     * @param isDefault 默认地址标识
     * @see com.wangguang.model.enums.EnumDefault
     */
    public void setIsDefault(byte isDefault) {
        this.isDefault = isDefault;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
