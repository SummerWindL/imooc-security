package com.imooc.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.imooc.security.core.properties.SecurityProperties;

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
	
	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()//表单属于认证
		.loginPage("/authentication/require")
		.loginProcessingUrl("/authentication/form")//做这个配置是当提交该请求的时候，spring sercurity知道需要用UsernamePasswordAuthenticationFilter来处理该请求  
//		http.httpBasic()//基于httpbasic认证
		.and()
		.authorizeRequests()
		.antMatchers("/authentication/require",
				securityProperties.getBrowser().getLoginPage()).permitAll()//当访问匹配到的url时，不需要身份认证就可以访问，其他都需要
		.anyRequest()
		.authenticated()//表单登陆（安全有两个，认证，授权）
		.and()
		.csrf().disable();//禁用csrf跨站请求防护功能
	}
	
}
