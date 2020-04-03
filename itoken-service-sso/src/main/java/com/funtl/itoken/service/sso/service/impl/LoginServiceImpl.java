package com.funtl.itoken.service.sso.service.impl;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.service.redis.service.RedisService;
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
    private RedisService redisService;

    @Autowired
    private TbSysUserMapper tbSysUserMapper;

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        TbSysUser tbSysUser=null;
        String json = (String)redisService.get(loginCode);

        //缓存中没有数据，从数据库中取数据
        if(json==null){
            Example example = new Example(TbSysUser.class);
            example.createCriteria().andEqualTo("loginCode",loginCode);
            tbSysUser = tbSysUserMapper.selectOneByExample(example);
            String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
            if(password.equals(tbSysUser.getPassword())){
                //放入缓存中
                try {
                    redisService.put(loginCode,MapperUtils.obj2json(tbSysUser),60*60*24);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return tbSysUser;
            }else{
                return null;
            }
        }

        //缓存中有数据
        else {
            if(!"not_ok".equals(json)){
                try {
                    tbSysUser=MapperUtils.json2pojo(json,TbSysUser.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return tbSysUser;
    }
}
