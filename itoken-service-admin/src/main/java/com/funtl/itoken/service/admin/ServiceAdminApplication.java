package com.funtl.itoken.service.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/3/31 20:37
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.funtl.itoken.service.admin.mapper"})
public class ServiceAdminApplication {
}
