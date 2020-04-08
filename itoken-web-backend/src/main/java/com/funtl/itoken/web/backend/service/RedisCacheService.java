package com.funtl.itoken.web.backend.service;

import com.funtl.itoken.web.backend.service.fallback.RedisCacheServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/8 11:13
 * @Version 1.0
 **/
@FeignClient(value = "itoken-service-redis", fallback = RedisCacheServiceFallback.class)
public interface RedisCacheService {
    @RequestMapping(value = "put", method = RequestMethod.POST)
    public String put(
            @RequestParam(value = "key") String key,
            @RequestParam(value = "value") String value,
            @RequestParam(value = "seconds") long seconds);

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "key") String key);
}
