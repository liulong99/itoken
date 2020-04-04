package com.funtl.itoken.web.admin.interceptor;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.utils.CookieUtils;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.web.admin.service.RedisCacheService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/4 21:50
 * @Version 1.0
 **/
public class WebAdminInterceptor implements HandlerInterceptor {

    @Resource
    private RedisCacheService redisCacheService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "token");
        //token为空表示一定没有登录
        if(StringUtils.isBlank(token)){
            //踢回单点登录页面
            response.sendRedirect("http://localhost:8503/login?url=http://localhost:8601");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        TbSysUser tbSysUser = (TbSysUser) session.getAttribute("tbSysUser");
        //已登录状态
        if(tbSysUser!=null){
            if(modelAndView!=null){
                modelAndView.addObject("tbSysUser");
            }
        }
        //未登录状态
        else{
            String token=CookieUtils.getCookieValue(request,"token");
            if(StringUtils.isNotBlank("token")){
                String loginCode = redisCacheService.get(token);
                if(StringUtils.isNotBlank(loginCode)){
                    String json = redisCacheService.get(loginCode);
                    if(StringUtils.isNotBlank(json)){
                        tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                        if(modelAndView!=null){
                            modelAndView.addObject("tbSysUser");
                        }
                        //已登录状态 创建局部会话
                        request.getSession().setAttribute("tbSysUser",tbSysUser);
                    }
                }
            }
        }

        //二次确认是否有用户信息
        if(tbSysUser==null){
            response.sendRedirect("http://localhost:8503/login?url=http://localhost:8601");
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
