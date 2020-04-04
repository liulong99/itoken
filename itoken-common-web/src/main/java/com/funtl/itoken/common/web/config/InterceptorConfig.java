package com.funtl.itoken.common.web.config;

import com.funtl.itoken.common.web.interceptor.ConstantsInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/4 15:05
 * @Version 1.0
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public ConstantsInterceptor constantsInterceptor(){
        return new ConstantsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //常量拦截器
        registry.addInterceptor(constantsInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**");
    }
}
