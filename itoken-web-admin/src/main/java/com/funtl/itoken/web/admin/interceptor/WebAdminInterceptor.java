package com.funtl.itoken.web.admin.interceptor;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.utils.CookieUtils;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.common.web.constants.WebConstants;
import com.funtl.itoken.common.web.utils.HttpServletUtils;
import com.funtl.itoken.web.admin.service.RedisCacheService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/4 21:50
 * @Version 1.0
 **/
public class WebAdminInterceptor implements HandlerInterceptor {

    @Resource
    private RedisCacheService redisCacheService;

    @Value("${hosts.sso}")
    private String HOSTS_SSO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, WebConstants.SESSION_TOKEN);
        // token 为空表示一定没有登录
        if (StringUtils.isBlank(token)) {
            try {
                response.sendRedirect(String.format("%s/login?url=%s", HOSTS_SSO, HttpServletUtils.getFullPath(request)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        TbSysUser tbSysUser = (TbSysUser) session.getAttribute(WebConstants.SESSION_USER);

        // 已登录状态
        if (tbSysUser != null) {
            if (modelAndView != null) {
                modelAndView.addObject(WebConstants.SESSION_USER, tbSysUser);
            }
        }

        // 未登录状态
        else {
            String token=CookieUtils.getCookieValue(request,WebConstants.SESSION_USER);
            if (StringUtils.isNotBlank(token)) {
                String loginCode=redisCacheService.get(token);
                if(StringUtils.isNotBlank(loginCode)){
                    String json=redisCacheService.get(loginCode);
                    if(StringUtils.isNotBlank(json)){
                        // 已登录状态，创建局部会话
                        tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                        if (modelAndView != null) {
                            modelAndView.addObject(WebConstants.SESSION_USER, tbSysUser);
                        }
                        request.getSession().setAttribute(WebConstants.SESSION_USER, tbSysUser);
                    }
                }
            }
        }

        // 二次确认是否有用户信息
        if (tbSysUser == null) {
            try {
                response.sendRedirect(String.format("%s/login?url=%s", HOSTS_SSO, HttpServletUtils.getFullPath(request)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
