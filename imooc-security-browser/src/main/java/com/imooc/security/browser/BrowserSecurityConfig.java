package com.imooc.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *@date 2018年6月11日-下午11:06:15
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()//表单属于认证
//		http.httpBasic()//基于httpbasic认证
		.and()
		.authorizeRequests()
		.anyRequest()
		.authenticated();//表单登陆（安全有两个，认证，授权）
	}
	
}
