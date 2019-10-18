package com.bootdo.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bootdo.common.interceptor.IndexInterceptor;

@Component
class WebConfigurer implements  WebMvcConfigurer  {
	@Autowired
	BootdoConfig bootdoConfig;
	
	@Autowired
	IndexInterceptor indexInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 自定义拦截器，添加拦截路径和排除拦截路径
		registry.addInterceptor(indexInterceptor).addPathPatterns("/zy/**");
	}
}