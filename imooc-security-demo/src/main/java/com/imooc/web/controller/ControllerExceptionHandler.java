package com.imooc.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.imooc.exception.UserNotExistException;

/**
 *@date 2018年6月5日-上午12:22:10
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String,Object> handlerUserNotException(UserNotExistException ux){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("id", ux.getId());
		map.put("message", ux.getMessage());
		return map;
	}
}
