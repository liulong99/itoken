package com.funtl.itoken.web.admin.controller;

import com.funtl.itoken.web.admin.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/2 16:06
 * @Version 1.0
 **/
@Controller
public class AdminController {


    @Resource
    private AdminService adminService;

    /**
   * 跳转登录页
     * @return
     */
    @RequestMapping(value = {"","login"},method = RequestMethod.GET)
    public String login(){
        String json=adminService.login("","");
        System.out.println(json);
        return "hello";
    }

}