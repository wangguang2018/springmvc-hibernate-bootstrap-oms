package com.wangguang.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 所有控制器的基类
 *
 * @author ranfi
 */
@ControllerAdvice
public abstract class WebController {

   

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



}
