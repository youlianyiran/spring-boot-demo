package com.atian.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by xutiantian on 2017/3/2.
 */
@Configuration
public class SelfWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter
{
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        // 多个拦截器组成一个拦截器链
        registry.addInterceptor(new CrossInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
