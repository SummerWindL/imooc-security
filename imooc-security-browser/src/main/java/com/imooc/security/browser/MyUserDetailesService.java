package com.imooc.security.browser;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *@date 2018年6月12日-下午8:36:25
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
@Component
public class MyUserDetailesService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(MyUserDetailesService.class);

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//根据用户名查找用户信息
		logger.info(username+"：你即将失去宝宝 ):");
		//根据查找到的用户信息判断用户是否被冻结
		String password = passwordEncoder.encode("123456");
		logger.info("数据库密码是："+password);
		//AuthorityUtils.commaSeparatedStringToAuthorityList("admin");这个工具返回授权的集合。
		return new User(username, password,true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
