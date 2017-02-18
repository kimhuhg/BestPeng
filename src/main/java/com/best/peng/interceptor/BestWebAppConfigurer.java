package com.best.peng.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class BestWebAppConfigurer extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用于排除拦截
		registry.addInterceptor(new BestHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/login").
		excludePathPatterns("/register").excludePathPatterns("/account/**").excludePathPatterns("/error");
		
		super.addInterceptors(registry);
	}
}
