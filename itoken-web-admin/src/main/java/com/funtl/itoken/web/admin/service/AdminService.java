package com.funtl.itoken.web.admin.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/2 15:55
 * @Version 1.0
 **/
public interface AdminService {
    /**
     * 根据 ID 获取管理员
     *
     * @return
     */
    @RequestMapping(value = "v1/admins", method = RequestMethod.GET)
    public String get(
            @RequestParam(required = true, value = "userCode") String userCode
    );
}
