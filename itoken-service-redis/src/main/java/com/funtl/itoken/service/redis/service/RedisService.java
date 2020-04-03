package com.funtl.itoken.service.redis.service;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/3 0:09
 * @Version 1.0
 **/
public interface RedisService {

    public void put(String key,Object value,long seconds);
    public Object get(String key);
}
