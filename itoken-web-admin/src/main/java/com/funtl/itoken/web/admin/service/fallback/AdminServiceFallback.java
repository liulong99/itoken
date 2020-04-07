package com.funtl.itoken.web.admin.service.fallback;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.web.admin.service.AdminService;
import org.springframework.stereotype.Component;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/2 15:59
 * @Version 1.0
 **/
@Component
public class AdminServiceFallback implements AdminService{

    @Override
    public String get(String userCode) {
        try {
            String json = MapperUtils.obj2json(new TbSysUser());
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
