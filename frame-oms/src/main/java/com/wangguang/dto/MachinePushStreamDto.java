package com.wangguang.dto;

import java.io.Serializable;

/**
 * 推流参数
 *
 * @Desc com.dm.web.dto
 * @Author peakren
 * @Date 09/02/2018 6:23 PM
 */
public class MachinePushStreamDto implements Serializable {

    private static final long serialVersionUID = -1772041615108145925L;

    /**
     * 请求命令
     */
    protected String cmd;

    /**
     * 设备号
     */
    protected String vmcNo;

    private Long appid;  //推流ID

    private String appsecret;   //推流密钥

    private Integer useTestEnv;  //使用测试环境

    private Integer rotationAngle;   //摄像头旋转角度


    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getVmcNo() {
        return vmcNo;
    }

    public void setVmcNo(String vmcNo) {
        this.vmcNo = vmcNo;
    }

    public Long getAppid() {
        return appid;
    }

    public void setAppid(Long appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public Integer getUseTestEnv() {
        return useTestEnv;
    }

    public void setUseTestEnv(Integer useTestEnv) {
        this.useTestEnv = useTestEnv;
    }

    public Integer getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(Integer rotationAngle) {
        this.rotationAngle = rotationAngle;
    }
}
