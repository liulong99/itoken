package com.funtl.itoken.service.sso.service.consumer;


import com.funtl.itoken.service.sso.service.consumer.fallback.RedisCacheServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "itoken-service-redis",fallback = RedisCacheServiceFallback.class)
public interface RedisCacheService {

    @RequestMapping(value = "put",method = RequestMethod.POST)
    public String put(@RequestParam("key")String key,@RequestParam("value")String value,@RequestParam("seconds")long seconds);

    @RequestMapping(value = "get",method = RequestMethod.GET)
    public String get(@RequestParam("key")String key);
}
