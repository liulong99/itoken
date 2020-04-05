package com.funtl.itoken.service.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/3/31 20:37
 * @Version 1.0
 **/
@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication(scanBasePackages = "com.funtl.itoken")
@MapperScan(basePackages = {"com.funtl.itoken.common.mapper","com.funtl.itoken.service.admin.mapper"})
public class ServiceAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAdminApplication.class,args);
    }
}
