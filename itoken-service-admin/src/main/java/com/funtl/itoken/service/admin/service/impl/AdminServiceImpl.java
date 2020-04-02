package com.funtl.itoken.service.admin.service.impl;

import com.funtl.itoken.service.admin.domain.TbSysUser;
import com.funtl.itoken.service.admin.mapper.TbSysUserMapper;
import com.funtl.itoken.service.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/3/31 21:36
 * @Version 1.0
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private TbSysUserMapper tbSysUserMapper;

    /**
     * 注册
     * @param tbSysUser
     */
    @Override
    @Transactional(readOnly = false)
    public void register(TbSysUser tbSysUser) {
        tbSysUser.setPassword(DigestUtils.md5DigestAsHex(tbSysUser.getPassword().getBytes()));
        tbSysUserMapper.insert(tbSysUser);
    }

    /**
     * 登录
     * @param loginCode 登录账号
     * @param plantPassword 明文登录密码
     * @return
     */
    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        Example example = new Example(TbSysUser.class);
        example.createCriteria().andEqualTo("loginCode",loginCode).andEqualTo("plantPassword",plantPassword);
        TbSysUser tbSysUser = tbSysUserMapper.selectOneByExample(example);
        if(tbSysUser != null){
            String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
            if(password.equals(tbSysUser.getPassword())){
                return tbSysUser;
            }
        }
        return null;
    }
}
