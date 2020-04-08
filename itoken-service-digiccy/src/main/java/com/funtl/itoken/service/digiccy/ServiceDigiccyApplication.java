package com.funtl.itoken.service.digiccy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/8 11:23
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.funtl.itoken.common.mapper", "com.funtl.itoken.service.digiccy.mapper"})
public class ServiceDigiccyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDigiccyApplication.class, args);
    }
}
