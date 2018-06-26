package com.iguangtech.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangguang.services.ExceptionCode;

import java.util.LinkedHashMap;

/**
 * UserInfoDto - 请求响应
 */
public class ResponseDTO extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = -1671607286610174732L;

    /**
     * 实例化一个请求响应
     */
    public ResponseDTO() {
        addAttribute("errCode", ExceptionCode.NORMAL.errorCode);
        addAttribute("errMsg", ExceptionCode.NORMAL.errorMsg);
    }

    public ResponseDTO(Object result) {
        addAttribute("errCode", ExceptionCode.NORMAL.errorCode);
        addAttribute("errMsg", ExceptionCode.NORMAL.errorMsg);
        addAttribute("data", result);
    }

    public ResponseDTO(ExceptionCode e){
        addAttribute("errCode", e.errorCode);
        addAttribute("errMsg", e.errorMsg);
    }

    /**
     * 实例化一个请求响应
     *
     * @param errorCode 错误码
     * @param errorMsg  错误消息
     */
    public ResponseDTO(Integer errorCode, String errorMsg) {
        addAttribute("errCode", errorCode);
        addAttribute("errMsg", errorMsg);
    }

    @JsonIgnore
    public Integer getErrorCode() {
        return Integer.parseInt(get("errCode").toString());
    }

    public void setErrorCode(Integer errorCode) {
        addAttribute("errCode", errorCode);
    }

    @JsonIgnore
    public String getErrorMsg() {
        return get("errMsg").toString();
    }

    public void setErrorMsg(String errorMsg) {
        addAttribute("errMsg", errorMsg);
    }

    @JsonIgnore
    public Object getResult() {
        return get("data");
    }

    public void setResult(Object result) {
        addAttribute("data", result);
    }

    public ResponseDTO addAttribute(String key, Object value) {
        put(key, value);
        return this;
    }
}
