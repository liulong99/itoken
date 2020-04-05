package com.funtl.itoken.service.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/5 16:45
 * @Version 1.0
 **/
@EnableSwagger2
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.funtl.itoken")
@MapperScan(basePackages = {"com.funtl.itoken.common.mapper", "com.funtl.itoken.service.posts.mapper"})
public class ServicePostsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePostsApplication.class,args);
    }
}
