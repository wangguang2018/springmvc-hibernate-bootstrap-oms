package com.iguangtech.api.controller;

import com.iguangtech.api.common.Constant;
import com.wangguang.services.cache.AccessTokenCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller - 基类
 *
 * @author xingkong1221
 * @since 2015-12-17
 */
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AccessTokenCache accessTokenCache;

    /**
     * 获取登录帐号编号
     *
     * @return 登录帐号编号
     */
    public Integer getLoginMemberId() {
        return (Integer) request.getAttribute(Constant.MEMBER_ID);
    }

    public String getAccessToken() {
        return request.getParameter("accessToken");
    }

    public Integer getAgentId(){
        return (Integer) request.getAttribute(Constant.AGENT_ID);
    }


    /**
     * 通过AccessToken获取用户uid
     * 该方法用于没有@NeedToken注解时
     *
     * @return
     */
    public Integer getMemberByAccessToken() {
        // 获取accessToken
        Integer uid = null;
        String accessToken = request.getParameter("accessToken");
        // 判断accessToken参数是否存在
        if (StringUtils.isBlank(accessToken)) {
            return uid;
        }
        // 判断accessToken是否合法
        if (!accessTokenCache.isTokenExists(accessToken)) {
            return uid;
        }
        uid = accessTokenCache.getUid(accessToken);
        return uid;
    }


}
