package com.funtl.itoken.web.admin.service.fallback;

import com.funtl.itoken.web.admin.service.RedisCacheService;
import org.springframework.stereotype.Component;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/3 20:20
 * @Version 1.0
 **/
@Component
public class RedisCacheServiceFallback implements RedisCacheService {
    @Override
    public String put(String key, String value, long seconds) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }
}
