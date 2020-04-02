package com.funtl.itoken.web.admin.service;

import com.funtl.itoken.web.admin.service.fallback.AdminServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/2 15:55
 * @Version 1.0
 **/
@FeignClient(value = "itoken-service-admin",fallback = AdminServiceFallback.class)
public interface AdminService {

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(@RequestParam(value = "loginCode")String loginCode, @RequestParam(value = "password")String password);
}
