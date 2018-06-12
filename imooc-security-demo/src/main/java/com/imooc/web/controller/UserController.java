package com.imooc.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import com.imooc.exception.UserNotExistException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 *@date 2018年5月25日-下午10:25:33
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
@RestController
@RequestMapping("/user")
public class UserController{

	@PostMapping
	public User create(@Valid @RequestBody User user) {
		
		/*if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));;
		}*/
		
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		
		user.setId("1");
		return user;
	}
	
	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user,BindingResult errors) {
		
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> {
//				FieldError fieldError = (FieldError)error;
//				String message = fieldError.getField() +" "+ error.getDefaultMessage();
				System.out.println(error.getDefaultMessage());
			} );
		}
		
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		
		user.setId("1");
		return user;
	}
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println(id);
		
	}
	
	@GetMapping
	@JsonView(User.UserSimpleView.class)
	@ApiOperation(value="用户查询服务")
	public List<User> query(UserQueryCondition condiction/*@RequestParam(value ="username" ,required = false,defaultValue = "tom") String nickname,*/
			,@PageableDefault(page = 2,size = 17,sort="username,asc") Pageable page){
		
//		System.out.println(nickname);
		System.out.println(ReflectionToStringBuilder.toString(condiction,ToStringStyle.MULTI_LINE_STYLE) );
		
		System.out.println(page.getPageSize());
		System.out.println(page.getPageNumber());
		System.out.println(page.getSort());
		List<User> user = new ArrayList<>();
		user.add(new User());
		user.add(new User());
		user.add(new User());
		return user;
	}
	
	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@ApiParam("用户id")@PathVariable ( name = "id") String idxxx) {
		
//		throw new UserNotExistException(idxxx);
		
		System.out.println("进入getInfo服务");
		
		User user = new User();
		user.setUsername("tom");
		return user;
	}
	
}

























