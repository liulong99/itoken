package com.funtl.itoken.web.admin.service.fallback;

import com.funtl.itoken.common.dto.BaseResult;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.web.admin.service.AdminService;
import com.google.common.collect.Lists;
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
    public String login(String loginCode, String password) {
        BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("502", "从上游服务器接收到无效的响应")));
        try {
            return MapperUtils.obj2json(baseResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
