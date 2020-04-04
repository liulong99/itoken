package com.funtl.itoken.web.admin.config;

import com.funtl.itoken.web.admin.interceptor.WebAdminInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebInterceptorConfiguration implements WebMvcConfigurer {

    @Bean
    public WebAdminInterceptor loginInterceptor() {
        return new WebAdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**");
    }
}
