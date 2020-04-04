package com.funtl.itoken.service.admin.Controller;

import com.funtl.itoken.service.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/2 14:27
 * @Version 1.0
 **/
@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

}
