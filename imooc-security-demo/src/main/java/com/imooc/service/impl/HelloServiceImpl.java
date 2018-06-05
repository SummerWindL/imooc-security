package com.imooc.service.impl;

import org.springframework.stereotype.Service;

import com.imooc.service.HelloService;

/**
 *@date 2018年6月4日-下午11:11:14
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {

	@Override
	public String greeting(String name) {
		System.out.println("greeting");
		return "hello" + name;
	}

}
