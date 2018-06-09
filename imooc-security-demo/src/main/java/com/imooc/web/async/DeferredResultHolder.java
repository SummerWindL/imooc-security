package com.imooc.web.async;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 *@date 2018年6月9日-下午4:27:16
 *@author fu yanliang
 *@action(作用):传递deferredResult对象
 *@instruction
 */
@Component
public class DeferredResultHolder {

	private Map<String,DeferredResult<String>> map = new HashMap<String, DeferredResult<String>>();

	public Map<String, DeferredResult<String>> getMap() {
		return map;
	}

	public void setMap(Map<String, DeferredResult<String>> map) {
		this.map = map;
	} 
	
}
