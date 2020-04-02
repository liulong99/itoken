package com.funtl.itoken.service.admin.test.service;
import java.util.Date;
import java.util.UUID;

import com.funtl.itoken.service.admin.ServiceAdminApplication;
import com.funtl.itoken.service.admin.domain.TbSysUser;
import com.funtl.itoken.service.admin.service.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/3/31 21:46
 * @Version 1.0
 **/
@SpringBootTest(classes = ServiceAdminApplication.class)
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class adminServiceTest {

    @Autowired
    AdminService adminService;

    @Test
    public void register(){
        TbSysUser tbSysUser = new TbSysUser();
        tbSysUser.setUserCode(UUID.randomUUID().toString());
        tbSysUser.setLoginCode("706750966@qq.com");
        tbSysUser.setUserName("liu long");
        tbSysUser.setPassword("123456");
        tbSysUser.setUserType("0");
        tbSysUser.setMgrType("1");
        tbSysUser.setStatus("0");
        tbSysUser.setCreateBy(tbSysUser.getUserCode());
        tbSysUser.setCreateDate(new Date());
        tbSysUser.setUpdateBy(tbSysUser.getUserCode());
        tbSysUser.setUpdateDate(new Date());
        tbSysUser.setCorpCode("0");
        tbSysUser.setCorpName("iToken");
        adminService.register(tbSysUser);
    }

    @Test
    public void login(){
        TbSysUser tbSysUser = adminService.login("706750966@qq.com","123456");
        Assert.assertNotNull(tbSysUser);
    }

}
