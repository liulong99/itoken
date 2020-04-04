package com.funtl.itoken.common.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/4 15:01
 * @Version 1.0
 **/
public class ConstantsInterceptor implements HandlerInterceptor {

    private static final String HOST_CDN="http://192.168.0.116:81";
    private static final String TEMPLATE_ADMIN_LTE = "adminlte/v2.4.3";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(modelAndView!=null){
            modelAndView.addObject("adminlte",HOST_CDN + "/" + TEMPLATE_ADMIN_LTE);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
