package com.wangguang.exception;/*
package com.wangguang.exception;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class MyExceptionResolver extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        */
/*if(ex instanceof NumberFormatException){
            ModelAndView view = getModelAndView("error/NumberFormatExceptions",ex);
            return view;
        }*//*


        // 判断是否 Ajax 请求
        if ((request.getHeader("accept").indexOf("application/json") > -1 ||
                (request.getHeader("X-Requested-With") != null &&
                        request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))){

            try {
                response.setContentType("application/json;charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write("{\"code\":2002}");
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        return super.doResolveException(request, response, handler, ex);
    }


}
*/
