package com.wangguang.dto;

import java.io.Serializable;

/**
 * @Desc com.dm.web.dto
 * @Author peakren
 * @Date 09/02/2018 9:30 PM
 */
public class BaseDto implements Serializable {

    private static final long serialVersionUID = -4167712907250833540L;

    /**
     * 请求命令
     */
    protected String cmd;

    /**
     * 设备号
     */
    protected String vmcNo;

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
}
