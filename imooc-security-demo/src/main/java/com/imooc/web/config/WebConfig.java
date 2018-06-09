package com.imooc.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.imooc.web.filter.TimeFilter;
import com.imooc.web.interceptor.TimeInterceptor;

/**
 *@date 2018年6月5日-下午11:14:17
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

	@Autowired
	public TimeInterceptor timeInterceptor;
	
	/**
	 * 配置异步请求的拦截
	 */
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		super.configureAsyncSupport(configurer);
	}

	//	@Bean
	public FilterRegistrationBean timeFilter() {
		
		FilterRegistrationBean registBean = new FilterRegistrationBean();
		
		TimeFilter timeFilter = new TimeFilter();
		
		registBean.setFilter(timeFilter);
		
		List<String> url = new ArrayList<String>();
		
		url.add("/*");
		registBean.setUrlPatterns(url);
		
		return registBean;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
//		registry.addInterceptor(timeInterceptor);
	}
	
	
}
