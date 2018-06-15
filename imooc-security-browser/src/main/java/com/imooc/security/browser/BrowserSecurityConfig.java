package com.imooc.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeFilter;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *@date 2018年6月11日-下午11:06:15
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler imoocAuthenticationFailureHandler;
	
	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
		
		
		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin()//表单属于认证
			.loginPage("/authentication/require")
			.loginProcessingUrl("/authentication/form")//做这个配置是当提交该请求的时候，spring sercurity知道需要用UsernamePasswordAuthenticationFilter来处理该请求  
			.successHandler(imoocAuthenticationSuccessHandler)
			.failureHandler(imoocAuthenticationFailureHandler)
	//		http.httpBasic()//基于httpbasic认证
			.and()
			.authorizeRequests()
			.antMatchers("/authentication/require",
					securityProperties.getBrowser().getLoginPage(),
					"/code/image").permitAll()//当访问匹配到的url时，不需要身份认证就可以访问，其他都需要
			.anyRequest()
			.authenticated()//表单登陆（安全有两个，认证，授权）
			.and()
			.csrf().disable();//禁用csrf跨站请求防护功能
	}
	
}
