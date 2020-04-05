package com.funtl.itoken.web.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/5 22:23
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = "com.funtl.itoken")
@EnableDiscoveryClient
@EnableFeignClients
public class WebPostsApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebPostsApplication.class,args);
    }
}
