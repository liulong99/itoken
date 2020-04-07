package com.funtl.itoken.service.sso.controller;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.utils.CookieUtils;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.service.sso.service.LoginService;
import com.funtl.itoken.service.sso.service.consumer.RedisCacheService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/3 21:17
 * @Version 1.0
 **/
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Resource
    private RedisCacheService redisCacheService;

    /**
     * 跳转登录页
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model,
                        @RequestParam(required = false) String url){
        String token=CookieUtils.getCookieValue(request,"token");
        if(StringUtils.isNotBlank(token)){
            String loginCode = redisCacheService.get(token);
            if(StringUtils.isNotBlank(loginCode)){
                String json = redisCacheService.get(loginCode);
                if(StringUtils.isNotBlank(json)){
                    try {
                        TbSysUser tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                        if(tbSysUser!=null){
                            if(StringUtils.isNotBlank(url)){
                                return "redirect:"+url;
                            }
                        }
                        model.addAttribute("tbSysUser",tbSysUser);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(StringUtils.isNotBlank(url)){
            model.addAttribute("url",url);
        }
        return "login";
    }

    /**
     * 登录业务
     * @param loginCode
     * @param plantPassword
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String loginCode, String plantPassword,
            @RequestParam(required = false)String url, RedirectAttributes redirectAttributes,
            HttpServletRequest request, HttpServletResponse response){
        TbSysUser tbSysUser= loginService.login(loginCode, plantPassword);
        if(tbSysUser==null){
            redirectAttributes.addFlashAttribute("message","用户名密码不一致,请重新输入");
        }else{
            String token= UUID.randomUUID().toString();
            String result = redisCacheService.put(token, loginCode, 60 * 60 * 24);
            if("ok".equals(result)&& StringUtils.isNotBlank(result)){
                CookieUtils.setCookie(request,response,"token",token,60 * 60 * 24);
                if(StringUtils.isNotBlank(url)){
                    return "redirect:"+url;
                }
            }else{
                //熔断
                redirectAttributes.addFlashAttribute("message","服务器异常，请稍后再试");
            }
        }
        return "redirect:/login";
    }

    /**
     * 注销
     * @param request
     * @param response
     * @param url
     * @param model
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(required = false)String url, Model model){
        try {
            CookieUtils.deleteCookie(request, response, "token");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (StringUtils.isNotBlank(url)) {
            return "redirect:/login?url=" + url;
        } else {
            return "redirect:/login";
        }
    }

}
