package com.jias.page.config;

import com.jias.page.intercept.RequestIntercept;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author Mi
 * @version 1.0
 * @description: TODO
 * @date 2022/4/17 9:47
 */
@Configuration
public class SpringWebMvcConfig implements WebMvcConfigurer {

    @Resource
    private RequestIntercept requestIntercept;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestIntercept);
    }
}

