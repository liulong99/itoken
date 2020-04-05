package com.funtl.itoken.web.posts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/5 22:30
 * @Version 1.0
 **/
@Controller
public class WebPostsController {

    @RequestMapping(value = {"","index"},method = RequestMethod.GET)
    public String index(){
        return "index";
    }
}
