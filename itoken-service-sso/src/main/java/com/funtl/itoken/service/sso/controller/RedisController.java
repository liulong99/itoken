package com.funtl.itoken.service.sso.controller;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.service.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/3 21:17
 * @Version 1.0
 **/
@Controller
public class RedisController {

    @Autowired
    private LoginService loginService;

    /**
     * 跳转登录页
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "hello";
    }

    /**
     * 登录业务
     * @param loginCode
     * @param plantPassword
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String loginCode,String plantPassword){
        TbSysUser tbSysUser= loginService.login(loginCode, plantPassword);
        System.out.println(tbSysUser);
        return "ok";
    }


}
