package com.lecloud.springbootlearn.config;

import com.lecloud.springbootlearn.service.HandlerInterceptorService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Resource
    private HandlerInterceptorService handlerInterceptorService;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptorService).addPathPatterns("/*").excludePathPatterns("/httptest");
        super.addInterceptors(registry);
    }
}

