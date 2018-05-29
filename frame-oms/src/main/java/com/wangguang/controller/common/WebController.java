package com.wangguang.controller.common;

import com.wangguang.entity.Agent;
import com.wangguang.model.enums.EnumAdminType;
import com.wangguang.model.sys.Role;
import com.wangguang.model.sys.User;
import com.wangguang.service.AgentService;
import com.wangguang.web.JsonMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 所有控制器的基类
 *
 * @author ranfi
 */
@ControllerAdvice
public abstract class WebController {

    @Resource
    private AgentService agentService;

   

    /**
     * log4j 记录器
     */
    protected static final Logger log = LoggerFactory.getLogger(WebController.class);


    @ExceptionHandler({RuntimeException.class})
    public ModelAndView webDataException(RuntimeException ex, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("error/500");
        model.addObject("errMsg", "系统不给力,请稍后再试!");
        model.addObject("ctx", request.getContextPath());
        return model;
    }


    /**
     * 解析绑定结果
     *
     * @param result 绑定结果
     * @param msg    消息
     * @return Json返回对象
     */
    public JsonMap parseErrorResult(BindingResult result, String msg) {
        JsonMap ret = new JsonMap(-10, msg);
        Map<String, String> errors = new HashMap<String, String>();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ret.addAttribute("errors", errors);
        return ret;
    }

    /**
     * 获取登陆用户
     *
     * @return 登陆用户
     */
    public User getLoginUser() {
        //ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();

       /* if (user != null) {
            return accountService.findUserByAccount(user.getAccount());
        }*/
        return null;
    }


    public boolean isAgent(){
        User user =  getLoginUser();
        List<Role> roles = user.getRoleList();
        for(Role role : roles){
            if(role.getId().intValue() != EnumAdminType.ADMIN.value){
                return true;
            }
        }
        return false;
    }

    public Agent getLoginAgent(){
        User user =  getLoginUser();
        return agentService.findByAdminId(user.getId());
    }



}
