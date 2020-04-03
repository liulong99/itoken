package com.funtl.itoken.service.sso.service.impl;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.service.sso.mapper.TbSysUserMapper;
import com.funtl.itoken.service.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/3 19:54
 * @Version 1.0
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TbSysUserMapper tbSysUserMapper;

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        Example example = new Example(TbSysUser.class);
        example.createCriteria().andEqualTo("loginCode",loginCode);
        TbSysUser tbSysUser = tbSysUserMapper.selectOneByExample(example);
        String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
        if(password.equals(tbSysUser.getPassword())){
            return tbSysUser;
        }
        return null;
    }
}
