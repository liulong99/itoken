package com.funtl.itoken.service.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/7 19:45
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class ServiceUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUploadApplication.class,args);
    }
}
