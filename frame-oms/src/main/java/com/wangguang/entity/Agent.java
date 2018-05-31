package com.wangguang.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.wangguang.model.BaseEntity;
import com.wangguang.model.sys.User;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * 代理商
 */
@Entity
public class Agent extends BaseEntity {
    private String name;
    private String phone;
    private String contact;
    private Integer adminId;
    private String mail;

    /**
     * 类型：1：代理商 2：渠道商
     * @see com.wangguang.model.enums.EnumAgentType
     */
    private Integer type = 1;

    private String key;
    private String jpushKey;
    private String jpushSecret;
    private String alipayAppId;
    private String alipayPrivateKey;
    private String alipayPublicKey;
    private String alipayAppPublicKey;
    private String wxApiCertName;
    private String wxAppId;
    private String wxMchId;
    private String wxPayKey;
    private String wxGzhAppId;
    private String wxGzhAppSecret;
    private String smsAccount;
    private String smsPwd;
    private String smsUrl;
    private String smsSignature;
    private String wxAppSecret;
    private Byte status;
    private User user;


    private String mailSmtp;        // 邮箱主机地址
    private String mailFrom;        // 发送邮箱
    private String mailPassword;    // 邮箱密码

    /**
     * 父级代理商id
     */
    private Integer pid = 0;

    /**
     * 支付宝回调url
     */
    private String alipayNotifyUrl;

    /**
     * 微信回调url
     */
    private String wechatNotifyUrl;


    /**
     * 即构id
     */
    private String zegoId;

    /**
     * 即构key
     */
    private String zegoKey;

    /**
     * 即构server
     */
    private String zegoServer;


    /**
     *环信的orgName
     */
    private String huanxinOrgName;


    /**
     * 环信的appName
     */
    private String huanxinAppName;


    /**
     *环信的clientId
     */
    private String huanxinClientId;

    /**
     * 环信的clientSecret
     */
    private String huanxinClientSecret;

    /**
     * 代理商分享用URL
     */
    private String shareUrl;

    /**
     * OSS 相关配置信息
     */
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String bucketUrl;

    /**
     * 小程序
     */
    private String xcxAppId;
    private String xcxAppSecret;
    private String xcxMchId;        // 小程序支付商户号
    private String xcxPayKey;       // 小程序支付支付秘钥
    private String xcxCertName;     // 小程序支付证书路径
    private String xcxNotifyUrl;    // 小程序支付通知地址

    /**
     * 微信公众号商户号
     */
    private String wxGzhMchId;

    /**
     * 微信公众号支付密钥
     */
    private String wxGzhPayKey;

    /**
     * 微信公众号证书文件名
     */
    private String wxGzhCertName;

    /**
     * 微信公众号支付回调
     */
    private String wxGzhNotifyUrl;



    public String getWxGzhMchId() {
        return wxGzhMchId;
    }

    public void setWxGzhMchId(String wxGzhMchId) {
        this.wxGzhMchId = wxGzhMchId;
    }

    public String getWxGzhPayKey() {
        return wxGzhPayKey;
    }

    public void setWxGzhPayKey(String wxGzhPayKey) {
        this.wxGzhPayKey = wxGzhPayKey;
    }

    public String getWxGzhCertName() {
        return wxGzhCertName;
    }

    public void setWxGzhCertName(String wxGzhCertName) {
        this.wxGzhCertName = wxGzhCertName;
    }

    public String getWxGzhNotifyUrl() {
        return wxGzhNotifyUrl;
    }

    public void setWxGzhNotifyUrl(String wxGzhNotifyUrl) {
        this.wxGzhNotifyUrl = wxGzhNotifyUrl;
    }

    public String getHuanxinClientId() {
        return huanxinClientId;
    }

    public void setHuanxinClientId(String huanxinClientId) {
        this.huanxinClientId = huanxinClientId;
    }

    public String getHuanxinOrgName() {
        return huanxinOrgName;
    }

    public void setHuanxinOrgName(String huanxinOrgName) {
        this.huanxinOrgName = huanxinOrgName;
    }

    public String getHuanxinAppName() {
        return huanxinAppName;
    }

    public void setHuanxinAppName(String huanxinAppName) {
        this.huanxinAppName = huanxinAppName;
    }

    public String getHuanxinClientSecret() {
        return huanxinClientSecret;
    }

    public void setHuanxinClientSecret(String huanxinClientSecret) {
        this.huanxinClientSecret = huanxinClientSecret;
    }

    public String getZegoId() {
        return zegoId;
    }

    public void setZegoId(String zegoId) {
        this.zegoId = zegoId;
    }

    public String getZegoKey() {
        return zegoKey;
    }

    public void setZegoKey(String zegoKey) {
        this.zegoKey = zegoKey;
    }

    public String getZegoServer() {
        return zegoServer;
    }

    public void setZegoServer(String zegoServer) {
        this.zegoServer = zegoServer;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getAlipayNotifyUrl() {
        return alipayNotifyUrl;
    }

    public void setAlipayNotifyUrl(String alipayNotifyUrl) {
        this.alipayNotifyUrl = alipayNotifyUrl;
    }

    public String getWechatNotifyUrl() {
        return wechatNotifyUrl;
    }

    public void setWechatNotifyUrl(String wechatNotifyUrl) {
        this.wechatNotifyUrl = wechatNotifyUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSmsAccount() {
        return smsAccount;
    }

    public void setSmsAccount(String smsAccount) {
        this.smsAccount = smsAccount;
    }

    public String getSmsPwd() {
        return smsPwd;
    }

    public void setSmsPwd(String smsPwd) {
        this.smsPwd = smsPwd;
    }

    public String getSmsUrl() {
        return smsUrl;
    }

    public void setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }

    public String getJpushKey() {
        return jpushKey;
    }

    public void setJpushKey(String jpushKey) {
        this.jpushKey = jpushKey;
    }

    public String getJpushSecret() {
        return jpushSecret;
    }

    public void setJpushSecret(String jpushSecret) {
        this.jpushSecret = jpushSecret;
    }

    public String getAlipayAppId() {
        return alipayAppId;
    }

    public void setAlipayAppId(String alipayAppId) {
        this.alipayAppId = alipayAppId;
    }

    public String getAlipayPrivateKey() {
        return alipayPrivateKey;
    }

    public void setAlipayPrivateKey(String alipayPrivateKey) {
        this.alipayPrivateKey = alipayPrivateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getWxApiCertName() {
        return wxApiCertName;
    }

    public void setWxApiCertName(String wxApiCertName) {
        this.wxApiCertName = wxApiCertName;
    }

    public String getWxAppId() {
        return wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    public String getWxMchId() {
        return wxMchId;
    }

    public void setWxMchId(String wxMchId) {
        this.wxMchId = wxMchId;
    }

    public String getWxPayKey() {
        return wxPayKey;
    }

    public void setWxPayKey(String wxPayKey) {
        this.wxPayKey = wxPayKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Column(name = "admin_id")
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Column(name = "secret_key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", insertable = false, updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAlipayAppPublicKey() {
        return alipayAppPublicKey;
    }

    public void setAlipayAppPublicKey(String alipayAppPublicKey) {
        this.alipayAppPublicKey = alipayAppPublicKey;
    }

    public String getSmsSignature() {
        return smsSignature;
    }

    public void setSmsSignature(String smsSignature) {
        this.smsSignature = smsSignature;
    }

    public String getWxAppSecret() {
        return wxAppSecret;
    }

    public void setWxAppSecret(String wxAppSecret) {
        this.wxAppSecret = wxAppSecret;
    }

    public String getWxGzhAppId() {
        return wxGzhAppId;
    }

    public void setWxGzhAppId(String wxGzhAppId) {
        this.wxGzhAppId = wxGzhAppId;
    }

    public String getWxGzhAppSecret() {
        return wxGzhAppSecret;
    }

    public void setWxGzhAppSecret(String wxGzhAppSecret) {
        this.wxGzhAppSecret = wxGzhAppSecret;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @JsonProperty("mail_smtp")
    public String getMailSmtp() {
        return mailSmtp;
    }

    public void setMailSmtp(String mailSmtp) {
        this.mailSmtp = mailSmtp;
    }

    @JsonProperty("mail_from")
    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    @JsonProperty("mail_password")
    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketUrl() {
        return bucketUrl;
    }

    public void setBucketUrl(String bucketUrl) {
        this.bucketUrl = bucketUrl;
    }

    public String getXcxAppId() {
        return xcxAppId;
    }

    public void setXcxAppId(String xcxAppId) {
        this.xcxAppId = xcxAppId;
    }

    public String getXcxAppSecret() {
        return xcxAppSecret;
    }

    public void setXcxAppSecret(String xcxAppSecret) {
        this.xcxAppSecret = xcxAppSecret;
    }

    public String getXcxMchId() {
        return xcxMchId;
    }

    public void setXcxMchId(String xcxMchId) {
        this.xcxMchId = xcxMchId;
    }

    public String getXcxPayKey() {
        return xcxPayKey;
    }

    public void setXcxPayKey(String xcxPayKey) {
        this.xcxPayKey = xcxPayKey;
    }

    public String getXcxCertName() {
        return xcxCertName;
    }

    public void setXcxCertName(String xcxCertName) {
        this.xcxCertName = xcxCertName;
    }

    public String getXcxNotifyUrl() {
        return xcxNotifyUrl;
    }

    public void setXcxNotifyUrl(String xcxNotifyUrl) {
        this.xcxNotifyUrl = xcxNotifyUrl;
    }
}
