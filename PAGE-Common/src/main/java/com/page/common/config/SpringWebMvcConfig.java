package com.page.common.config;

import com.page.common.intercept.RequestIntercept;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


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

    /**
     * 配置拦截规则与注入拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPattern 添加拦截规则 /** 拦截所有包括静态资源
        // excludePathPattern 排除拦截规则 所以我们需要放开静态资源的拦截
        registry.addInterceptor(requestIntercept)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/validateCode")
                .excludePathPatterns("/system/aesKey")
                .excludePathPatterns("/css/**", "/fonts/**", "/images/**", "/js/**");
    }
}

