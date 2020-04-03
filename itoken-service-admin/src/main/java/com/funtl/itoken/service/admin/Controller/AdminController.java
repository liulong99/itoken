package com.funtl.itoken.service.admin.Controller;

import com.funtl.itoken.common.dto.BaseResult;
import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.service.admin.service.AdminService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    /**
     * 登录
     * @param loginCode
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public BaseResult login(String loginCode,String password){
        //检查登录
        BaseResult baseResult = checkLogin(loginCode, password);
        if(baseResult!=null){
            return baseResult;
        }
        TbSysUser tbSysUser = adminService.login(loginCode, password);
        //登录成功
        if(tbSysUser!=null){
            return BaseResult.ok(tbSysUser);
        }
        //登录失败
        else{
            return BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("","登录失败")));
        }
    }

    /**
     * 检查登录
     * @param loginCode
     * @param password
     * @return
     */
    private BaseResult checkLogin(String loginCode,String password){
        BaseResult baseResult=null;
        if(StringUtils.isBlank(loginCode)||StringUtils.isBlank(password)){
            baseResult=BaseResult.notOk(Lists.newArrayList(
                    new BaseResult.Error("loginCode","登录账号不能为空"),
                    new BaseResult.Error("password","密码不能为空")
            ));
        }
        return baseResult;
    }

}
