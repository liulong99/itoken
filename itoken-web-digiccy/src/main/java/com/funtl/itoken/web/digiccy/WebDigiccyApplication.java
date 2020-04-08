package com.funtl.itoken.web.digiccy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/8 11:44
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = "com.funtl.itoken")
@EnableDiscoveryClient
@EnableFeignClients
public class WebDigiccyApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebDigiccyApplication.class, args);
    }
}
