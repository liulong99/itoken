package com.funtl.itoken.web.posts.interceptor;

import com.funtl.itoken.common.utils.CookieUtils;
import com.funtl.itoken.common.web.constants.WebConstants;
import com.funtl.itoken.common.web.interceptor.BaseInterceptorMethods;
import com.funtl.itoken.web.posts.service.RedisCacheService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/4 21:50
 * @Version 1.0
 **/
public class WebPostsInterceptor implements HandlerInterceptor {

    @Resource
    private RedisCacheService redisCacheService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return BaseInterceptorMethods.preHandleForLogin(request, response, handler, "http://localhost:8602/" + request.getServletPath());
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String token = CookieUtils.getCookieValue(request, WebConstants.SESSION_TOKEN);
        if (StringUtils.isNotBlank(token)) {
            String loginCode = redisCacheService.get(token);
            if (StringUtils.isNotBlank(loginCode)) {
                BaseInterceptorMethods.postHandleForLogin(request, response, handler, modelAndView, redisCacheService.get(loginCode), "http://localhost:8602/" + request.getServletPath());
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
