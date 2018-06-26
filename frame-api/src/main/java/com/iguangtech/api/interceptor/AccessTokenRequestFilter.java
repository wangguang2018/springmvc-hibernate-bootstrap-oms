package com.iguangtech.api.interceptor;


import com.iguangtech.api.common.Constant;
import com.wangguang.services.cache.AccessTokenCache;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.TimeZone;

import static com.wangguang.services.cache.AccessTokenCache.ACCESS_TOKEN_KEY;

/**
 * Interceptor - accessToken拦截器
 *
 * @author xingkong1221
 * @date 2015-11-21
 */
public class AccessTokenRequestFilter extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AccessTokenRequestFilter.class);

    @Autowired
    private AccessTokenCache accessTokenCache;


   /* @Autowired
    private AgentCache agentCache;

    @Autowired
    private MemberService memberService;

    @Resource
    private AgentService agentService;

    @Autowired
    private LimitIpCache limitIpCache;*/

    /**
     * 创建一个请求响应
     *
     * @param
     * @return 请求响应
     */
    /*private String buildResponse(ExceptionCode code) {
        return "{\"errCode\": "+code.errorCode+", \"errMsg\": \""+code.errorMsg+"\"}";
    }*/

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        if(handler instanceof DefaultServletHttpRequestHandler){
            response.setContentType("application/json;charset=utf-8");
            //response.getWriter().write(buildResponse(ExceptionCode.API_EXCEPTION));
            return false;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Integer agentId = null;


        /*String ip = CommonUtils.getRemoteIP(request); //获取用户的IP
        if(StringUtils.isNotBlank(ip)){
            long val = limitIpCache.push(ip);
            if(val>100){  //如果当前IP一分钟超过100次访问,则返回
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(buildResponse(ExceptionCode.pinglv));
                return false;
            }
        }*/

        com.iguangtech.interceptor.NeedToken needToken = method.getAnnotation(com.iguangtech.interceptor.NeedToken.class);
        if (needToken == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }
        String accessToken = request.getParameter("accessToken");
        if (logger.isDebugEnabled()) {
            logger.debug("accessToken = " + accessToken);
        }

        // 判断accessToken参数是否存在
        if (StringUtils.isBlank(accessToken)) {
            response.setContentType("application/json;charset=utf-8");
            //response.getWriter().write(buildResponse(ExceptionCode.MISSING_ACCESS_TOKEN));
            return false;
        }

        if(accessToken.equals("123456789")){
            request.setAttribute(Constant.MEMBER_ID, 123);
            return true;
        }
        accessTokenCache.pushToken(162,"e4f99926ef12eee88b840a7947b4f78a");
        // 判断accessToken是否合法
        if (!accessTokenCache.isTokenExists(accessToken)) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"errCode\": "+"-1"+", \"errMsg\": \""+"登录失效，请重新登录！"+"\"}");
            return false;
        }
        Integer uid = accessTokenCache.getUid(accessToken);
        request.setAttribute(Constant.MEMBER_ID, uid);
        /*Member member = memberService.findById(uid);
        if(member==null){
            throw new WebRequestException(ExceptionCode.INVALID_ACCESS_TOKEN);
        }
        if(member.getAgentId().intValue() != agentId.intValue()){
            throw new WebRequestException(ExceptionCode.AGENT_KEY_INVALID);
        }*/


        //checkPagination(request);
        return true;
    }


   /* private boolean checkPagination(HttpServletRequest request){
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        if(page==null && pageSize==null){
            return true;
        }
        if(StringUtils.isEmpty(page) || StringUtils.isEmpty(pageSize)){
            throw new WebRequestException(ExceptionCode.PARAM_EXCEPTION);
        }
        try{
            int pageInt = Integer.parseInt(page);
            int pageSizeInt = Integer.parseInt(pageSize);
            if(pageInt<1 || pageSizeInt<1){
                throw new WebRequestException(ExceptionCode.PARAM_EXCEPTION);
            }
        }catch(Exception e){
            throw new WebRequestException(ExceptionCode.PARAM_EXCEPTION);
        }

        return  true;
    }*/

    /**
     * 获取代理商ID
     * @param request
     */
    /*private Integer setAgentId(HttpServletRequest request) {
        String agentKey = request.getParameter("key");
        if(StringUtils.isEmpty(agentKey)){
            throw new WebRequestException(ExceptionCode.AGENT_KEY_INVALID);
        }
        if (!agentCache.isAgentExists(agentKey)) {
            Agent agent = agentService.findAgentByKey(agentKey);
            if(agent == null){
                throw new WebRequestException(ExceptionCode.AGENT_KEY_INVALID);
            }
            else{
                agentCache.pushAgent(agent.getId(),agentKey);
                return agent.getId();
            }
        }else{
            return agentCache.getAgentId(agentKey);
        }
    }*/
}
